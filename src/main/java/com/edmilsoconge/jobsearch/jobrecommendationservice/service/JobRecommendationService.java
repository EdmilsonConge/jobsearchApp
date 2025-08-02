package com.edmilsoconge.jobsearch.jobrecommendationservice.service;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationResponse;
import com.edmilsoconge.jobsearch.jobapplicationservice.service.ApplicationService;
import com.edmilsoconge.jobsearch.jobrecommendationservice.dto.RecommendationRequest;
import com.edmilsoconge.jobsearch.jobrecommendationservice.dto.RecommendationResponse;
import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;
import com.edmilsoconge.jobsearch.postservice.service.PostService;
import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import com.edmilsoconge.jobsearch.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRecommendationService {
    private final PostService postService;
    private final UserService userService;
    private final ApplicationService applicationService;
    private final OllamaService ollamaService;
    private final MatchingAlgorithmService matchingAlgorithmService;


    public JobRecommendationService(
            PostService postService,
            UserService userService,
            ApplicationService applicationService,
            OllamaService ollamaService,
            MatchingAlgorithmService matchingAlgorithmService) {
        this.postService = postService;
        this.userService = userService;
        this.applicationService = applicationService;
        this.ollamaService = ollamaService;
        this.matchingAlgorithmService = matchingAlgorithmService;
    }

    public List<RecommendationResponse>  getRecommendationsForUser(String userId) {
        return generateRecommendations(new RecommendationRequest(userId, 10, true));
    }

    public List<RecommendationResponse>  getRecommendationsForApplication(String userId, int limit) {
        return generateRecommendations(new RecommendationRequest(userId, limit, true));
    }

    public List<RecommendationResponse> generateRecommendations(RecommendationRequest request) {
        UserResponse user = userService.findUserById(request.userId());

        List<PostDTO> allPosts = postService.findAllPosts();

        List<PostDTO> availablePosts = filterAppliedJobs(allPosts, request);

        List<RecommendationResponse> recommendations = availablePosts.stream()
                .map(post -> createRecommendation(user, post))
                .sorted((r1, r2) -> Double.compare(r2.matchScore(), r1.matchScore()))
                .limit(request.maxRecommendations())
                .collect(Collectors.toList());

        return enrichWithAIAnalysis(recommendations, user);

    }

    private List<PostDTO> filterAppliedJobs(List<PostDTO> allPosts, RecommendationRequest request) {
        if(!request.excludeAppliedJobs()){
            return allPosts;
        }

        List<String> appliedPostIds = applicationService.findApplicationsByUserId(request.userId())
                .stream()
                .map(ApplicationResponse::postId)
                .toList();

        return allPosts.stream()
                .filter(postDTO -> !appliedPostIds.contains(postDTO.id()))
                .collect(Collectors.toList());
    }

    private RecommendationResponse createRecommendation(UserResponse userResponse, PostDTO postDTO){
        double matchScore = matchingAlgorithmService.calculateMatchScore(userResponse, postDTO);
        String reason = matchingAlgorithmService.generateMatchReason(userResponse, postDTO, matchScore);

        return new RecommendationResponse(
                postDTO.id(),
                postDTO,
                matchScore,
                reason,
                ""
        );
    }

    private List<RecommendationResponse> enrichWithAIAnalysis(List<RecommendationResponse> recommendations, UserResponse userResponse){
        return recommendations.stream()
                .map(rec -> {
                    String aiAnalysis = ollamaService.generateJobAnalysis(userResponse, rec.post());
                    return new RecommendationResponse(
                            rec.postId(),
                            rec.post(),
                            rec.matchScore(),
                            rec.reasonForRecommendation(),
                            aiAnalysis
                    );
                })
                .collect(Collectors.toList());
    }
}