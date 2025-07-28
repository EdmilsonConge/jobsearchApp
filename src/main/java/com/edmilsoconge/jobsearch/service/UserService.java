package com.edmilsoconge.jobsearch.service;

import com.edmilsoconge.jobsearch.dto.UserRequest;
import com.edmilsoconge.jobsearch.dto.UserResponse;
import com.edmilsoconge.jobsearch.mapper.UserRequestMapper;
import com.edmilsoconge.jobsearch.mapper.UserResponseMapper;
import com.edmilsoconge.jobsearch.model.User;
import com.edmilsoconge.jobsearch.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserRequest userDTO) {
        User user = UserRequestMapper.toUser(userDTO);
        userRepository.save(user);
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserResponseMapper::toUserDto)
                .toList();
    }

    public UserResponse findUserById(String id) {
        return userRepository.findById(id)
                .map(UserResponseMapper::toUserDto)
                .orElse(null);
    }


    public void updateUser(String id, UserRequest userDTO) {
        userRepository.findById(id).ifPresent(existingUser -> {
            User updated = UserRequestMapper.toUser(userDTO);
            updated.setId(id);
            userRepository.save(updated);
        });
    }

    public void deleteUser(String id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

}
