package com.edmilsoconge.jobsearch.jobrecommendationservice.controller;

import com.edmilsoconge.jobsearch.jobrecommendationservice.dto.RecommendationRequest;
import com.edmilsoconge.jobsearch.jobrecommendationservice.dto.RecommendationResponse;
import com.edmilsoconge.jobsearch.jobrecommendationservice.service.JobRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final JobRecommendationService recommendationService;

    public RecommendationController(JobRecommendationService recommendationService) {
            this.recommendationService = recommendationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationResponse>> getRecommendationsForUser(@PathVariable String userId) {
        List<RecommendationResponse> recommendations = recommendationService.getRecommendationsForUser(userId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<RecommendationResponse>> generateRecommendations(@RequestBody RecommendationRequest request) {
        List<RecommendationResponse> recommendations = recommendationService.generateRecommendations(request);
        return ResponseEntity.ok(recommendations);
    }

}
