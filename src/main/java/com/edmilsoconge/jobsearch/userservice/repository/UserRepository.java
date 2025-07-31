package com.edmilsoconge.jobsearch.userservice.repository;

import com.edmilsoconge.jobsearch.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
