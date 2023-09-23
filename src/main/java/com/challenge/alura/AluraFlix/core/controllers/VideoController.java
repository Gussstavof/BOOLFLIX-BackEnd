package com.challenge.alura.AluraFlix.core.controllers;

import com.challenge.alura.AluraFlix.core.entities.videos.VideoDto;
import com.challenge.alura.AluraFlix.core.services.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<VideoDto> videoResponseEntitySave(@Valid @RequestBody VideoDto videoDto,
                                                         URI location){
        return ResponseEntity.created(location).body(videoService.saveVideo(videoDto));
    }

    @GetMapping
    @Cacheable(value = "get_all_videos")
    public ResponseEntity<Page<VideoDto>> videoDtoResponseEntityGetAll(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(videoService.getAllVideos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> videoDtoResponseEntityGetById(@PathVariable String id){
        return ResponseEntity.ok(videoService.getByIdVideo(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<VideoDto> videoResponseEntityUpdate(@PathVariable String id,
                                                           @Valid @RequestBody VideoDto videoDto){
        return ResponseEntity.ok(videoService.updateVideo(id, videoDto));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<String> videoResponseEntityDelete(@PathVariable String id){
        videoService.deleteVideo(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<VideoDto>> videoResponseEntityGetByTitle(@RequestParam("title") String title,
    @PageableDefault  Pageable pageable){
        return ResponseEntity.ok(videoService.getByTitleVideo(title, pageable));
    }
}
