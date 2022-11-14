package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface VideoRepository extends MongoRepository<Video, String> {

    Set<Video> findByCategory(Category id);
}
