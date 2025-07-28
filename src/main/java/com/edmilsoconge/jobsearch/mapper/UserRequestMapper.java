package com.edmilsoconge.jobsearch.mapper;

import com.edmilsoconge.jobsearch.dto.UserRequest;
import com.edmilsoconge.jobsearch.model.User;

public class UserRequestMapper {

    public static UserRequest toUserDTO(User user) {
        return new UserRequest(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getDescription(),
                user.getStatus(),
                user.getExperiences(),
                user.getEducations());
    }

    public static User toUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setPhone(userRequest.phone());
        user.setDescription(userRequest.description());
        user.setStatus(userRequest.status());
        user.setExperiences(userRequest.experiences());
        user.setEducations(userRequest.educations());
        return user;
    }
}
