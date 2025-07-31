package com.edmilsoconge.jobsearch.jobapplicationservice.mapper;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationRequest;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.Application;
import com.edmilsoconge.jobsearch.userservice.dto.UserRequest;
import com.edmilsoconge.jobsearch.userservice.model.User;

import java.time.LocalDateTime;

public class ApplicationRequestMapper {

    public static ApplicationRequest toApplicationDTO(Application application) {
        return new ApplicationRequest(
                application.getUserId(),
                application.getPostId(),
                application.getCoverLetter(),
                application.getDocumentIds());
    }

    public static Application toApplication(ApplicationRequest applicationRequest) {
        Application application = new Application();
        application.setUserId(applicationRequest.userId());
        application.setPostId(applicationRequest.postId());
        application.setCoverLetter(applicationRequest.coverLetter());
        application.setDocumentIds(applicationRequest.documentIds());
        return application;
    }
}
