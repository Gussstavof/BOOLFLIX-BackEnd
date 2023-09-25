package com.challenge.alura.AluraFlix.core.entities;

import com.challenge.alura.AluraFlix.core.entities.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.core.entities.videos.VideoDto;
import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Mapper {
    public Video toVideo(VideoDto videoDto){
        return new Video(videoDto);
    }

    public VideoDto toVideoDto(Video video){
        return new VideoDto(video);
    }

    public Page<VideoDto> toVideoDto(Collection<Video> videos){
        return new PageImpl<>(videos.stream().map(VideoDto::new).collect(Collectors.toList()));
    }
}
