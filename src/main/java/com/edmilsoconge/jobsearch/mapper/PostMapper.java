package com.edmilsoconge.jobsearch.mapper;

import com.edmilsoconge.jobsearch.dto.PostDTO;
import com.edmilsoconge.jobsearch.model.Post;

public class PostMapper {

    public static PostDTO toDto(Post post) {
        return new PostDTO(
                post.getProfile(),
                post.getDescription(),
                post.getExperience(),
                post.getTechs());
    }

    public static Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setProfile(postDTO.profile());
        post.setDescription(postDTO.description());
        post.setExperience(postDTO.experience());
        post.setTechs(postDTO.techs());
        return post;
    }
}
