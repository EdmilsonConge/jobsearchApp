package com.edmilsoconge.jobsearch.jobrecommendationservice.dto;

import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;

public record RecommendationResponse(String postId, PostDTO post, double matchScore, String reasonForRecommendation, String aiAnalysis) {
}
