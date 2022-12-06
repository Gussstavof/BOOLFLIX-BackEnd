package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dto.Mapper;
import com.challenge.alura.AluraFlix.dto.VideoDto;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    public VideoDto saveVideo(VideoDto videoDto){
        searchCategory(videoDto);
        videoRepository.save(mapper.toVideo(videoDto));
        return videoDto;
    }

    public Page<VideoDto> getAllVideos(Pageable pageable){
        var page = videoRepository.findAll(pageable);
        return mapper.toVideoDto(page.getContent());
    }

    public VideoDto getByIdVideo(String id){
        return mapper.toVideoDto(getIdOrThrow(id));
    }

    public VideoDto updateVideo(String id, VideoDto videoDto) {
      return videoRepository.findById(id).map(videoUpdate -> {
          videoUpdate.setTitle(videoDto.getTitle());
          videoUpdate.setDescription(videoDto.getDescription());
          videoUpdate.setUrl(videoDto.getUrl());
          return mapper.toVideoDto(videoRepository.save(videoUpdate));
      }).orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

    public void deleteVideo(String id){
        videoRepository.deleteById(getIdOrThrow(id).getId());
    }

    private Video getIdOrThrow(String id){
        return videoRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

    private void searchCategory(VideoDto videoDto){
        videoDto.setCategory(
                categoryRepository.findById(videoDto.getCategory().getId())
                        .orElseThrow(() -> new ExceptionNotFound("Id not found")));
    }

    public Page<VideoDto> getByTitleVideo(String title, Pageable pageable) {
        var page = videoRepository.findByTitleContains(title, pageable);
        return mapper.toVideoDto(page.getContent());
    }
}
