package com.challenge.alura.AluraFlix.core.controllers;

import com.challenge.alura.AluraFlix.controllers.VideoController;
import com.challenge.alura.AluraFlix.core.dtos.videos.VideoRequest;
import com.challenge.alura.AluraFlix.core.dtos.videos.VideoResponse;
import com.challenge.alura.AluraFlix.core.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.net.URI;
import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
class VideoControllerTest {
    @InjectMocks
    private VideoController videoController;
    @Mock
    private VideoService videoService;

    VideoRequest videoUpdateRequest;
    VideoResponse videoUpdateResponse;
    VideoRequest videoRequest;
    VideoResponse videoResponse;
    URI location;
    Pageable pageable;

    @BeforeEach
    void setUp(){
        videoRequest = VideoRequest.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
        videoResponse = VideoResponse.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoUpdateResponse = VideoResponse.builder()
                .id("1")
                .title("testando2")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
        videoUpdateRequest = VideoRequest.builder()
                .id("1")
                .title("testando2")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void videoResponseEntitySaveTest(){
        when(videoService.saveVideo(videoRequest))
                .thenReturn(videoResponse);

        var result = videoController
                .videoResponseEntitySave(videoRequest, location);

        assertEquals(result, ResponseEntity.created(location).body(videoResponse));
    }

    @Test
    void videoDtoResponseEntityGetAllTest() {
        Page<VideoResponse> videosResponse = new PageImpl<>(Collections.singletonList(videoResponse));

        when(videoService.getAllVideos(pageable))
                .thenReturn(new PageImpl<>(List.of(videoResponse)));

       var result = videoController
                .videoDtoResponseEntityGetAll(pageable);

        assertEquals(ResponseEntity.ok(videosResponse), result);
    }

    @Test
    void videoDtoResponseEntityGetByIdTest() {
        when(videoService.getByIdVideo("1"))
                .thenReturn(videoResponse);

        var result = videoController
                .videoDtoResponseEntityGetById("1");

        assertEquals(result, ResponseEntity.ok(videoResponse));
    }

    @Test
    void videoResponseEntityUpdateTest(){
        when(videoService.updateVideo("1", videoUpdateRequest))
                .thenReturn(videoUpdateResponse);

        var result = videoController
                .videoResponseEntityUpdate("1", videoUpdateRequest);

        assertEquals(result, ResponseEntity.ok(videoUpdateResponse));
    }

    @Test
    void videoResponseEntityDeleteTest(){
        doNothing().when(videoService).deleteVideo("1");

        var result = videoController
                .videoResponseEntityDelete("1");

        assertSame(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void videoResponseEntityGetByTitleTest(){
        Page<VideoResponse> videosDto = new PageImpl<>(Collections.singletonList(videoResponse));

        when(videoService.getByTitleVideo("Java", pageable))
                .thenReturn(videosDto);

        var result = videoController.videoResponseEntityGetByTitle("Java", pageable);

        assertEquals(result, ResponseEntity.ok(videosDto));
    }
}