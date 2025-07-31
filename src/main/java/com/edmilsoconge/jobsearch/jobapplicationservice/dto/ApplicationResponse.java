package com.edmilsoconge.jobsearch.jobapplicationservice.dto;

import com.edmilsoconge.jobsearch.jobapplicationservice.model.ApplicationStatus;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.DocumentInfo;

import java.time.LocalDateTime;
import java.util.List;

public record ApplicationResponse(String userId, String postId, LocalDateTime appliedAt, ApplicationStatus status, String coverLetter, List<DocumentInfo> documentIds){
}
