package com.challenge.alura.AluraFlix.entities.dto;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.enums.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Video toVideo(VideoDto videoDto){
        return Video.builder()
                .description(videoDto.getDescription())
                .title(videoDto.getTitle())
                .url(videoDto.getUrl())
                .status(StatusEnum.CREATED)
                .build();
    }

    public List<VideoDto> toListVideoDtos(List<Video> video){
        return video.stream().map(VideoDto::new).collect(Collectors.toList());

    }


    public VideoDto toVideoDto(Video videoResponse){
        return VideoDto.builder()
                .title(videoResponse.getTitle())
                .url(videoResponse.getUrl())
                .status(videoResponse.getStatus())
                .build();
    }

}
