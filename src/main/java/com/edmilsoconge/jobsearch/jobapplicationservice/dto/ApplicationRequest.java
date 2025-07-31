package com.edmilsoconge.jobsearch.jobapplicationservice.dto;

import com.edmilsoconge.jobsearch.jobapplicationservice.model.DocumentInfo;

import java.util.List;

public record ApplicationRequest(String userId, String postId, String coverLetter, List<DocumentInfo> documentIds) {
}
