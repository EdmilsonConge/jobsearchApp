package com.edmilsoconge.jobsearch.jobapplicationservice.repository;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationResponse;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.Application;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.ApplicationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
    

    List<Application> findAllByPostId(String postId);

    List<Application> findAllByUserId(String userId);

    List<Application> findByAppliedAt(LocalDateTime appliedAt);

    List<Application> findByStatus(ApplicationStatus status);
}
