package com.edmilsoconge.jobsearch.repository;

import com.edmilsoconge.jobsearch.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
