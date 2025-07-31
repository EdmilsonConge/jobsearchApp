package com.edmilsoconge.jobsearch.jobapplicationservice.mapper;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationResponse;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.Application;
import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import com.edmilsoconge.jobsearch.userservice.model.User;

public class ApplicationResponseMapper {

    public static ApplicationResponse toApplicationDto(Application application) {
        return new ApplicationResponse(
                application.getUserId(),
                application.getPostId(),
                application.getAppliedAt(),
                application.getStatus(),
                application.getCoverLetter(),
                application.getDocumentIds());
    }

    public static Application toApplication(ApplicationResponse applicationResponse) {

        Application application = new Application();
        application.setUserId(applicationResponse.userId());
        application.setPostId(applicationResponse.postId());
        application.setAppliedAt(applicationResponse.appliedAt());
        application.setStatus(applicationResponse.status());
        application.setCoverLetter(applicationResponse.coverLetter());
        application.setDocumentIds(applicationResponse.documentIds());

        return application;
    }
}
