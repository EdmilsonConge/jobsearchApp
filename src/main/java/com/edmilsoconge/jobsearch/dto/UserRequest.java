package com.edmilsoconge.jobsearch.dto;

import com.edmilsoconge.jobsearch.model.Education;
import com.edmilsoconge.jobsearch.model.Experience;
import com.edmilsoconge.jobsearch.model.Status;

import java.util.List;

public record UserRequest(String name, String email, String password, String phone, String description, Status status, List<Experience> experiences, List<Education> educations) {
}
