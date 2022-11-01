package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Video;
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


    Video video;

    @BeforeEach
    void setUp() {

        video = Video.builder()
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void saveVideo() {
        when(videoRepository.save(any())).thenReturn(video);

        Video videoResponse = videoService.saveVideo(video);

        assertSame(videoResponse.getTitle(),"testando" );
    }

    @Test
    void  getAll(){
        var videos = Collections.singletonList(video);

        when(videoRepository.findAll())
                .thenReturn(videos);

        var videoResponse = videoService.getAll();

        assertSame(videoResponse.get(0), videos.get(0));
    }

    @Test
    void  getById(){
       when(videoRepository.findById("1"))
               .thenReturn(java.util.Optional.ofNullable(video));

       var videoResponse = videoService.getById("1");

       assertSame(videoResponse, video);
    }
}