package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public Video saveVideo(Video video){
        return repository.save(video);
    }
}
