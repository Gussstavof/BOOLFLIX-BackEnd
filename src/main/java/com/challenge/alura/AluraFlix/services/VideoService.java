package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.dto.Mapper;
import com.challenge.alura.AluraFlix.entities.dto.VideoDto;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private Mapper mapper;

    public VideoDto saveVideo(VideoDto videoDto){
        var response = repository.save(mapper.toVideo(videoDto));
        return mapper.toVideoDto(response);
    }

    public List<VideoDto> getAll(){
        return  mapper.toListVideoDtos(repository.findAll());
    }


}
