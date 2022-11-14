package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Video saveVideo(Video video){
        searchCategory(video);
        return videoRepository.save(video);
    }

    public List<Video> getAllVideos(){
        return  videoRepository.findAll();
    }

    public Video getByIdVideo(String id){
        return getIdOrThrow(id);
    }

    public Video updateVideo(String id, Video video) {
      return videoRepository.findById(id).map(videoUpdate -> {
          videoUpdate.setTitle(video.getTitle());
          videoUpdate.setDescription(video.getDescription());
          videoUpdate.setUrl(video.getUrl());
          return videoRepository.save(videoUpdate);
      }).orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

    public void deleteVideo(String id){
        videoRepository.deleteById(getIdOrThrow(id).getId());
    }

    private Video getIdOrThrow(String id){
        return videoRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

    private void searchCategory(Video video){
        video.setCategory(
                categoryRepository.findById(video.getCategory().getId())
                        .orElseThrow(() -> new ExceptionNotFound("Id not found")));
    }
}
