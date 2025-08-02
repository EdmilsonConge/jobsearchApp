package com.edmilsoconge.jobsearch.jobrecommendationservice.service;

import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;
import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OllamaService {

    private final ChatModel chatModel; // Inject your ChatModel here

    public OllamaService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateJobAnalysis(UserResponse userResponse, PostDTO postDTO) {
        String prompt = buildAnalysisPrompt(userResponse, postDTO);

        ChatResponse response = chatModel.call(
                new Prompt(prompt, OllamaOptions.builder()
                        .model("gemma3:latest")
                        .temperature(0.4)
                        .build())
        );

        return response.getResults().get(0).getOutput().getText();
    }

    public String recommendBestJobs(UserResponse user, List<PostDTO> posts) {
        String prompt = buildRecommendationPrompt(user, posts);

        ChatResponse response = chatModel.call(
                new Prompt(prompt, OllamaOptions.builder()
                        .model("gemma3:latest")
                        .temperature(0.3)
                        .build())
        );

        return response.getResult().getOutput().getText();
    }

    private String buildAnalysisPrompt(UserResponse user, PostDTO post) {
        return String.format("""
            Analyze why this job posting matches this user's profile:
            
            USER PROFILE:
            - Name: %s
            - Description: %s
            - Status: %s
            - Experiences: %s
            - Education: %s
            
            JOB POSTING:
            - Company: %s
            - Title: %s
            - Description: %s
            
            Provide a concise analysis (max 100 words) explaining:
            1. Key matching points
            2. Potential challenges
            3. Why this is a good fit
            
            Focus on skills, experience level, and career alignment.
            """,
                user.name(),
                user.description(),
                user.status(),
                formatExperiences(user),
                formatEducation(user),
                post.companyName(),
                post.jobTitle(),
                post.description()
        );
    }

    private String buildRecommendationPrompt(UserResponse user, List<PostDTO> posts) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Based on this user profile, rank and recommend the best job matches:\n\n");

        prompt.append("USER PROFILE:\n");
        prompt.append("- Name: ").append(user.name()).append("\n");
        prompt.append("- Description: ").append(user.description()).append("\n");
        prompt.append("- Status: ").append(user.status()).append("\n");
        prompt.append("- Experiences: ").append(formatExperiences(user)).append("\n");
        prompt.append("- Education: ").append(formatEducation(user)).append("\n\n");

        prompt.append("AVAILABLE JOBS:\n");
        for (int i = 0; i < posts.size(); i++) {
            PostDTO post = posts.get(i);
            prompt.append(String.format("%d. %s at %s - %s\n",
                    i + 1, post.jobTitle(), post.companyName(), post.description()));
        }

        prompt.append("\nProvide top 3 recommendations with brief explanations (max 150 words total).");

        return prompt.toString();
    }

    private String formatExperiences(UserResponse user) {
        if (user.experiences() == null || user.experiences().isEmpty()) {
            return "No experience listed";
        }
        return user.experiences().stream()
                .map(exp -> String.format("%s at %s (%s)", exp.getRole(), exp.getCompanyName(), exp.getStartDate()))
                .collect(Collectors.joining(", "));
    }

    private String formatEducation(UserResponse user) {
        if (user.educations() == null || user.educations().isEmpty()) {
            return "No education listed";
        }
        return user.educations().stream()
                .map(edu -> String.format("%s from %s", edu.getDegree(), edu.getInstitution()))
                .collect(Collectors.joining(", "));
    }
}