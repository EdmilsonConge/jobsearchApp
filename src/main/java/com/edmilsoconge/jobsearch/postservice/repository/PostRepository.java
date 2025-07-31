package com.edmilsoconge.jobsearch.postservice.repository;

import com.edmilsoconge.jobsearch.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
