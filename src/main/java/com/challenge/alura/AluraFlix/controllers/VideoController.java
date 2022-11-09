package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<Video> videoResponseEntitySave(@Valid @RequestBody Video video,
                                                         URI location){
        return ResponseEntity.created(location).body(videoService.saveVideo(video));
    }

    @GetMapping
    public ResponseEntity<List<Video>> videoDtoResponseEntityGetAll(){
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> videoDtoResponseEntityGetById(@PathVariable String id){
        return ResponseEntity.ok(videoService.getByIdVideo(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Video> videoResponseEntityUpdate(@PathVariable String id,
                                                           @Valid @RequestBody Video video){
        return ResponseEntity.ok(videoService.updateVideo(id, video));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> videoResponseEntityDelete(@PathVariable String id){
        videoService.deleteVideo(id);
        return ResponseEntity.ok("deleted");
    }
}
