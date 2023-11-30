package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.entities.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
