package com.edmilsoconge.jobsearch.controller;

import com.edmilsoconge.jobsearch.dto.UserRequest;
import com.edmilsoconge.jobsearch.dto.UserResponse;
import com.edmilsoconge.jobsearch.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userDTO) {
        System.out.println("Received user: " +userDTO.toString());
        userService.addUser(userDTO);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequest userDTO) {
        userService.updateUser(id, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
