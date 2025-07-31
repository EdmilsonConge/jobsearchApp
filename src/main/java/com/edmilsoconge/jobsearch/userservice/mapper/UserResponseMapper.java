package com.edmilsoconge.jobsearch.userservice.mapper;

import com.edmilsoconge.jobsearch.userservice.dto.UserResponse;
import com.edmilsoconge.jobsearch.userservice.model.User;

public class UserResponseMapper {

    public static UserResponse toUserDto(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDescription(),
                user.getStatus(),
                user.getExperiences(),
                user.getEducations());
    }

    public static User toUser(UserResponse userResponse) {
        User user = new User();
        user.setName(userResponse.name());
        user.setEmail(userResponse.email());
        user.setPhone(userResponse.phone());
        user.setDescription(userResponse.description());
        user.setStatus(userResponse.status());
        user.setExperiences(userResponse.experiences());
        user.setEducations(userResponse.educations());
        return user;
    }
}
