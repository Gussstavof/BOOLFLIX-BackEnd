package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.entities.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

}
