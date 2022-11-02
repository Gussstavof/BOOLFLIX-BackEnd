package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VideoServiceTest {

    @InjectMocks
    VideoService videoService;

    @Mock
    VideoRepository videoRepository;

    Video video;
    Video videoUpdate;

    @BeforeEach
    void setUp() {

        video = Video.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoUpdate = Video.builder()
                .id("1")
                .title("testando2")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void save_video() {
        when(videoRepository.save(any())).thenReturn(video);

        Video videoResponse = videoService.saveVideo(video);

        assertSame(videoResponse, video);
    }

    @Test
    void  get_all(){
        var videos = Collections.singletonList(video);

        when(videoRepository.findAll())
                .thenReturn(videos);

        var videoResponse = videoService.getAll();

        assertSame(videoResponse.get(0), videos.get(0));
    }

    @Test
    void  get_by_id(){
       when(videoRepository.findById("1"))
               .thenReturn(java.util.Optional.ofNullable(video));

       var videoResponse = videoService.getById("1");

       assertSame(videoResponse, video);
    }

    @Test
    void  get_by_id_NotFound(){
        when(videoRepository.findById("0"))
                .thenThrow(new ExceptionNotFound("Id not found"));

        assertThrows(ExceptionNotFound.class, () -> videoService.getById("0"));
    }

    @Test
    void update_video(){
        when(videoRepository.findById("1"))
                .thenReturn(Optional.ofNullable(video));
        when(videoRepository.save(videoUpdate))
                .thenReturn(videoUpdate);

        var result = videoService.update("1",videoUpdate);
        assertSame(result, videoUpdate);
    }
}