package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.entities.dto.Mapper;
import com.challenge.alura.AluraFlix.entities.dto.VideoDto;
import com.challenge.alura.AluraFlix.enums.StatusEnum;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VideoServiceTest {

    @InjectMocks
    VideoService videoService;

    @Mock
    VideoRepository videoRepository;

    @Mock
    Mapper mapper;

    VideoDto videoDto;
    VideoDto videoDtoResponse;
    Video video;

    @BeforeEach
    void setUp() {
        videoDto = VideoDto.builder()
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoDtoResponse = VideoDto.builder()
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .status(StatusEnum.CREATED)
                .build();

        video = Video.builder()
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .status(StatusEnum.CREATED)
                .build();
    }

    @Test
    void saveVideo() {
        when(videoRepository.save(any())).thenReturn(any());
        when(mapper.toVideo(videoDto)).thenReturn(any());
        when(mapper.toVideoDto(video)).thenReturn(videoDtoResponse);

        VideoDto videoDtoResponse = videoService.saveVideo(videoDto);

        assertSame(videoDtoResponse.getStatus(), StatusEnum.CREATED);
    }

    @Test
    void  getAll(){
        var videoDtos = Collections.singletonList(videoDtoResponse);
        var videos = Collections.singletonList(video);

        when(videoRepository.findAll()).thenReturn(videos);
        when(mapper.toListVideoDtos(videos)).thenReturn(videoDtos);
        var videoDtosResponse = videoService.getAll();
        assertSame(videoDtosResponse.get(0), videoDtosResponse.get(0));
    }
}