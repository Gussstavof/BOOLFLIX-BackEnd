package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.dtos.videos.VideoRequest;
import com.challenge.alura.AluraFlix.dtos.videos.VideoResponse;
import com.challenge.alura.AluraFlix.core.services.VideoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<VideoResponse> videoResponseEntitySave(
            @Valid @RequestBody VideoRequest videoRequest,
            URI location
    ) {
        return ResponseEntity.created(location).body(videoService.saveVideo(videoRequest));
    }

    @GetMapping
    @Cacheable(value = "get_all_videos")
    public ResponseEntity<Page<VideoResponse>> videoDtoResponseEntityGetAll(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(videoService.getAllVideos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponse> videoDtoResponseEntityGetById(@PathVariable String id) {
        return ResponseEntity.ok(videoService.getByIdVideo(id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<VideoResponse> videoResponseEntityUpdate(
            @PathVariable String id,
            @Valid @RequestBody VideoRequest videoRequest
    ) {
        return ResponseEntity.ok(videoService.updateVideo(id, videoRequest));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "get_all_videos", allEntries = true)
    public ResponseEntity<String> videoResponseEntityDelete(@PathVariable String id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<VideoResponse>> videoResponseEntityGetByTitle(
            @RequestParam("title") String title,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(videoService.getByTitleVideo(title, pageable));
    }
}
