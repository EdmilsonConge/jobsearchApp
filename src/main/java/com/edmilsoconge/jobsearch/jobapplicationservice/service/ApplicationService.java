package com.edmilsoconge.jobsearch.jobapplicationservice.service;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationRequest;
import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationResponse;
import com.edmilsoconge.jobsearch.jobapplicationservice.mapper.ApplicationRequestMapper;
import com.edmilsoconge.jobsearch.jobapplicationservice.mapper.ApplicationResponseMapper;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.Application;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.ApplicationStatus;
import com.edmilsoconge.jobsearch.jobapplicationservice.repository.ApplicationRepository;
import com.edmilsoconge.jobsearch.userservice.dto.UserRequest;
import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import com.edmilsoconge.jobsearch.userservice.mapper.UserRequestMapper;
import com.edmilsoconge.jobsearch.userservice.mapper.UserResponseMapper;
import com.edmilsoconge.jobsearch.userservice.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void apply(ApplicationRequest applicationRequest) {
        Application application = ApplicationRequestMapper.toApplication(applicationRequest);
        applicationRepository.save(application);
    }


    public ApplicationResponse findApplicationById(String id) {
        return applicationRepository.findById(id)
                .map(ApplicationResponseMapper::toApplicationDto)
                .orElse(null);
    }

    public List<ApplicationResponse> findAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(ApplicationResponseMapper::toApplicationDto)
                .toList();
    }

    public void deleteApplication(String id) {
        applicationRepository.findById(id).ifPresent(applicationRepository::delete);
    }

    public void updateApplication(String id, ApplicationRequest applicationRequest) {
        applicationRepository.findById(id).ifPresent(existingApplication -> {
            Application updated = ApplicationRequestMapper.toApplication(applicationRequest);
            updated.setId(id);
            applicationRepository.save(updated);
        });
    }

    public List<ApplicationResponse> findApplicationsByUserId(String userId) {
        return applicationRepository.findAllByUserId(userId)
                .stream()
                .map(ApplicationResponseMapper::toApplicationDto)
                .toList();
    }

    public List<ApplicationResponse> findApplicationsByPostId(String postId) {
        return applicationRepository.findAllByPostId(postId)
                .stream()
                .map(ApplicationResponseMapper::toApplicationDto)
                .toList();
    }

    public List<ApplicationResponse> findApplicationsByTimeAppliedAt(LocalDateTime appliedAt) {
        return applicationRepository.findByAppliedAt(appliedAt)
                .stream()
                .map(ApplicationResponseMapper::toApplicationDto)
                .toList();
    }

    public List<ApplicationResponse> findApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status)
                .stream()
                .map(ApplicationResponseMapper::toApplicationDto)
                .toList();
    }
}
