package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.entities.dto.Mapper;
import com.challenge.alura.AluraFlix.entities.dto.VideoDto;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/videos")
    public ResponseEntity<VideoDto> videoResponseEntitySave(@Valid @RequestBody VideoDto videoDto, URI location){
        return ResponseEntity.created(location).body(videoService.saveVideo(videoDto));
    }
}
