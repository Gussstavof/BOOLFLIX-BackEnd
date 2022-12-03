package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<Video> videoResponseEntitySave(@Valid @RequestBody Video video,
                                                         URI location){
        return ResponseEntity.created(location).body(videoService.saveVideo(video));
    }

    @GetMapping
    @Cacheable(value = "get_all_videos")
    public ResponseEntity<Page<Video>> videoDtoResponseEntityGetAll(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(videoService.getAllVideos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> videoDtoResponseEntityGetById(@PathVariable String id){
        return ResponseEntity.ok(videoService.getByIdVideo(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<Video> videoResponseEntityUpdate(@PathVariable String id,
                                                           @Valid @RequestBody Video video){
        return ResponseEntity.ok(videoService.updateVideo(id, video));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<String> videoResponseEntityDelete(@PathVariable String id){
        videoService.deleteVideo(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<Set<Video>> videoResponseEntityGetByTitle(@RequestParam("title") String title){
        return ResponseEntity.ok(videoService.getByTitleVideo(title));
    }
}
