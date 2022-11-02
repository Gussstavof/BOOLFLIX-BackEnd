package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.net.URI;
import java.util.Collections;
import java.util.Objects;


@ExtendWith(SpringExtension.class)
class VideoControllerTest {

    @InjectMocks
    private VideoController videoController;

    @Mock
    private VideoService videoService;

    Video video;
    Video videoUpdate;
    URI location;



    @BeforeEach
    void setUp(){
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
    void save_video_created_test(){
        when(videoService.saveVideo(video))
                .thenReturn(video);

        var result = videoController
                .videoResponseEntitySave(video, location);

        assertSame(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void get_all_videos_test() {
        var videos = Collections
                .singletonList(video);

        when(videoService.
                getAllVideos()).thenReturn(videos);

        var result = videoController
                .videoDtoResponseEntityGetAll();

        assertSame(result.getStatusCode(), HttpStatus.OK);
    }


    @Test
    void get_video_by_id_test_status_code() {
        when(videoService.getByIdVideo("1"))
                .thenReturn(video);

        var result = videoController
                .videoDtoResponseEntityGetById("1");

        assertSame(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void get_video_by_id_test_response_body() {
        when(videoService.getByIdVideo("1")).thenReturn(video);

        var result = videoController
                .videoDtoResponseEntityGetById("1");

        assertSame(Objects.requireNonNull
                (result.getBody()).getTitle(), "testando");
    }

    @Test
    void update_video(){
        when(videoService.updateVideo("1", videoUpdate))
                .thenReturn(videoUpdate);

        var result = videoController
                .videoResponseEntityUpdate("1", videoUpdate);

        assertSame(Objects.requireNonNull
                (result.getBody()).getTitle(), "testando2");
    }

}