package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public Video saveVideo(Video video){
        return repository.save(video);
    }

    public List<Video> getAllVideos(){
        return  repository.findAll();
    }

    public Video getByIdVideo(String id){
        return getIdOrThrow(id);
    }

    public Video updateVideo(String id, Video video) {
      return repository.findById(id).map(videoUpdate -> {
          videoUpdate.setTitle(video.getTitle());
          videoUpdate.setDescription(video.getDescription());
          videoUpdate.setUrl(video.getUrl());
          return repository.save(videoUpdate);
      }).orElseThrow(() -> new ExceptionNotFound("Id not found"));

    }

    public void deleteVideo(String id){
        repository.deleteById(getIdOrThrow(id).getId());
    }

    private Video getIdOrThrow(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

}
