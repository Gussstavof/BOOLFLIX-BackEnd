package com.challenge.alura.AluraFlix.core.services;

import com.challenge.alura.AluraFlix.core.dtos.videos.VideoRequest;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import com.challenge.alura.AluraFlix.core.dtos.videos.VideoResponse;
import com.challenge.alura.AluraFlix.core.exception.BadRequestException;
import com.challenge.alura.AluraFlix.core.exception.NotFoundException;
import com.challenge.alura.AluraFlix.core.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.core.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public VideoResponse saveVideo(VideoRequest videoRequest){
        searchCategory(videoRequest);
        Video video = videoRepository.save(videoRequest.toVideo());
        return new VideoResponse(video);
    }

    public Page<VideoResponse> getAllVideos(Pageable pageable){
        return new PageImpl<>(videoRepository.findAll(pageable)
                .stream()
                .map(VideoResponse::new)
                .collect(Collectors.toList())
        );
    }

    public VideoResponse getByIdVideo(String id){
        return new VideoResponse(getIdOrThrow(id));
    }

    public VideoResponse updateVideo(String id, VideoRequest videoRequest) {
      return videoRepository.findById(id).map(videoUpdate -> {
          videoUpdate.setTitle(videoRequest.getTitle());
          videoUpdate.setDescription(videoRequest.getDescription());
          videoUpdate.setUrl(videoRequest.getUrl());
          return new VideoResponse(videoRepository.save(videoUpdate));
      }).orElseThrow(() -> new NotFoundException("Video not found"));
    }

    public void deleteVideo(String id){
        videoRepository.deleteById(getIdOrThrow(id).getId());
    }

    public Page<VideoResponse> getByTitleVideo(String title, Pageable pageable) {
        return new PageImpl<>(videoRepository.findByTitleContains(title, pageable)
                .map(VideoResponse::new)
                .getContent()
        );
    }

    private Video getIdOrThrow(String id){
        return videoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("video not found"));
    }

    private void searchCategory(VideoRequest videoRequest){
        videoRequest.setCategory(
                categoryRepository.findById(videoRequest.getCategory().getId())
                        .orElseThrow(() -> new BadRequestException("category doesn't exist")));
    }
}
