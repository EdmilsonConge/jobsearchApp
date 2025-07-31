package com.edmilsoconge.jobsearch.userservice.dto;

import com.edmilsoconge.jobsearch.userservice.model.Education;
import com.edmilsoconge.jobsearch.userservice.model.Experience;
import com.edmilsoconge.jobsearch.userservice.model.Status;

import java.util.List;

public record UserRequest(String name, String email, String password, String phone, String description, Status status, List<Experience> experiences, List<Education> educations) {
}
