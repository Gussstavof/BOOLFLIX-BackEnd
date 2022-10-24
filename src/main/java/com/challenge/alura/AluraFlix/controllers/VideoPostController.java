package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("video/")
public class VideoPostController {

    @Autowired
    VideoRepository videoRepository;

    @PostMapping
    public ResponseEntity<Video> saveVideo(@RequestBody Video video, URI location){
        return ResponseEntity.created(location).body(videoRepository.save(video));
    }
}
