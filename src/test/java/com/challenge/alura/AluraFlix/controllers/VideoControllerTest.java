package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.http.HttpStatus;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.net.URI;
import java.util.Collections;


@ExtendWith(SpringExtension.class)
class VideoControllerTest {

    @InjectMocks
    private VideoController videoController;

    @Mock
    private VideoService videoService;

    Video video;
    URI location;



    @BeforeEach
    void setUp(){
        video = Video.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

    }

    @Test
    void save_video_created_test(){
        Mockito.when(videoService.saveVideo(video))
                .thenReturn(video);

        var result = videoController.videoResponseEntitySave(video, location);

        Assertions.assertSame(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void get_all_videos_test() {
        var videos = Collections.singletonList(video);
        Mockito.when(videoService.
                getAll()).thenReturn(videos);

        var result = videoController.videoDtoResponseEntityGetAll();

        Assertions.assertSame(result.getStatusCode(), HttpStatus.OK);
    }


    @Test
    void get_video_by_id_test_status_code() {
        Mockito.when(videoService.getById("1")).thenReturn(video);

        var result = videoController.videoDtoResponseEntityGetById("1");

        Assertions.assertSame(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void get_video_by_id_test_response_body() {
        Mockito.when(videoService.getById("1")).thenReturn(video);

        var result = videoController.videoDtoResponseEntityGetById("1");

        Assertions.assertSame(result.getBody().getTitle(), "testando");
    }

}