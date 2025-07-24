package com.edmilsoconge.jobsearch.service;

import com.edmilsoconge.jobsearch.PostDTO;
import com.edmilsoconge.jobsearch.PostMapper;
import com.edmilsoconge.jobsearch.model.Post;
import com.edmilsoconge.jobsearch.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService{

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> findAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toDto)
                .toList();
    }

    public void addPost(PostDTO postDTO) {
        Post post = PostMapper.toEntity(postDTO);
        postRepository.save(post);
    }

    public void updatePost(String id, PostDTO postDTO) {
        postRepository.findById(id).ifPresent(existing -> {
            Post updated = PostMapper.toEntity(postDTO);
            updated.setId(id);
            postRepository.save(updated);
        });
    }

    public void removePost(String id) {
        postRepository.findById(id).ifPresent(postRepository::delete);
    }

    public PostDTO findPostById(String id) {
        return postRepository.findById(id)
                .map(PostMapper::toDto)
                .orElse(null);
    }

}
