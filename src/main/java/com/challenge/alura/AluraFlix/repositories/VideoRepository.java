package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

    Page<Video> findByCategory(Category id, Pageable pageable);

    Page<Video> findByTitleContains(String title, Pageable pageable);
}
