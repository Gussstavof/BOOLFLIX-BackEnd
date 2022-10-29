package com.challenge.alura.AluraFlix.entities.dto;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.enums.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Video toVideo(VideoDto videoDto){
        return Video.builder()
                .description(videoDto.getDescription())
                .title(videoDto.getTitle())
                .url(videoDto.getUrl())
                .build();
    }

    public VideoDto setStatus(VideoDto videoDto){
        return VideoDto.builder()
                .title(videoDto.getTitle())
                .url(videoDto.getUrl())
                .status(StatusEnum.CREATED)
                .build();
    }

}
