package com.challenge.alura.AluraFlix.core.repositories;

import com.challenge.alura.AluraFlix.core.entities.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByUsername(String username);
}
