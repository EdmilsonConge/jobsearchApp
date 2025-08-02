package com.edmilsoconge.jobsearch.postservice.mapper;

import com.edmilsoconge.jobsearch.postservice.dto.PostDTO;
import com.edmilsoconge.jobsearch.postservice.model.Post;

public class PostMapper {

    public static PostDTO toDto(Post post) {
        return new PostDTO(
                post.getId(),
                post.getCompanyName(),
                post.getJobTitle(),
                post.getDescription());
    }

    public static Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.id());
        post.setCompanyName(postDTO.companyName());
        post.setJobTitle(postDTO.jobTitle());
        post.setDescription(postDTO.description());
        return post;
    }
}
