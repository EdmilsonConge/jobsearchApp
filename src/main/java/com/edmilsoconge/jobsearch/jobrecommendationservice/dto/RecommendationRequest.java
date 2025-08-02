package com.edmilsoconge.jobsearch.jobrecommendationservice.dto;

public record RecommendationRequest(String userId, int maxRecommendations, boolean excludeAppliedJobs) {
    public RecommendationRequest {
        if (maxRecommendations <= 0) {
            maxRecommendations = 10;
        }
    }
}
