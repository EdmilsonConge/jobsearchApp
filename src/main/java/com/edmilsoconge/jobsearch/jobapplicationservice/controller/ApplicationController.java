package com.edmilsoconge.jobsearch.jobapplicationservice.controller;

import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationRequest;
import com.edmilsoconge.jobsearch.jobapplicationservice.dto.ApplicationResponse;
import com.edmilsoconge.jobsearch.jobapplicationservice.model.ApplicationStatus;
import com.edmilsoconge.jobsearch.jobapplicationservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<String> addApplication(@RequestBody ApplicationRequest applicationRequest) {
         System.out.println("Received application: " +applicationRequest.toString());
         applicationService.apply(applicationRequest);
         return ResponseEntity.ok("Application added successfully");
    }

    @GetMapping
    public List<ApplicationResponse> getApplications() {
        return applicationService.findAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getUser(@PathVariable String id) {
        return applicationService.findApplicationById(id);
    }

    @GetMapping("/{userId}")
    public List<ApplicationResponse> getApplicationsByUserId(@PathVariable String userId) {
        return applicationService.findApplicationsByUserId(userId);
    }

    @GetMapping("{postId}")
    public List<ApplicationResponse> getApplicationsByPostId(@PathVariable String postId) {
        return applicationService.findApplicationsByPostId(postId);
    }

    @GetMapping("/{status}")
    public List<ApplicationResponse> getApplicationsByStatus(@PathVariable String status) {
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status);
        return applicationService.findApplicationsByStatus(applicationStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody ApplicationRequest applicationRequest) {
        applicationService.updateApplication(id, applicationRequest);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable String id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted successfully");
    }
}
