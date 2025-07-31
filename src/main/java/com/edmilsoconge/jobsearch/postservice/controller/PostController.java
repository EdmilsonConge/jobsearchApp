package com.edmilsoconge.jobsearch.postservice.controller;

import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;
import com.edmilsoconge.jobsearch.postservice.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job-posting")
public class PostController {

    PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }

    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        System.out.println("Received post: " + postDTO.toString());
        postService.addPost(postDTO);
        return ResponseEntity.ok("Post created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable String id, @RequestBody PostDTO postDTO) {
        postService.updatePost(id, postDTO);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        postService.removePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

}
