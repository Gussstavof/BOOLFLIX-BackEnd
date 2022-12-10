package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByUsername(String username);
}
