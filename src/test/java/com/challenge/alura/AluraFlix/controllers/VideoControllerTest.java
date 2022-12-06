package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.dto.VideoDto;
import com.challenge.alura.AluraFlix.services.VideoService;
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


@ExtendWith(SpringExtension.class)
class VideoControllerTest {
    @InjectMocks
    private VideoController videoController;
    @Mock
    private VideoService videoService;

    VideoDto videoUpdateDto;
    VideoDto videoDto;
    URI location;
    Pageable pageable;

    @BeforeEach
    void setUp(){
        videoDto = VideoDto.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoUpdateDto = VideoDto.builder()
                .id("1")
                .title("testando2")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void videoResponseEntitySaveTest(){
        when(videoService.saveVideo(videoDto))
                .thenReturn(videoDto);

        var result = videoController
                .videoResponseEntitySave(videoDto, location);

        assertEquals(result, ResponseEntity.created(location).body(videoDto));
    }

    @Test
    void videoDtoResponseEntityGetAllTest() {
        Page<VideoDto> videosDto = new PageImpl<>(Collections.singletonList(videoDto));

        when(videoService.getAllVideos(pageable))
                .thenReturn(videosDto);

       var result = videoController
                .videoDtoResponseEntityGetAll(pageable);

        assertEquals(result, ResponseEntity.ok(videosDto));
    }

    @Test
    void videoDtoResponseEntityGetByIdTest() {
        when(videoService.getByIdVideo("1"))
                .thenReturn(videoDto);

        var result = videoController
                .videoDtoResponseEntityGetById("1");

        assertEquals(result, ResponseEntity.ok(videoDto));
    }

    @Test
    void videoResponseEntityUpdateTest(){
        when(videoService.updateVideo("1", videoUpdateDto))
                .thenReturn(videoUpdateDto);

        var result = videoController
                .videoResponseEntityUpdate("1", videoUpdateDto);

        assertEquals(result, ResponseEntity.ok(videoUpdateDto));
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
        Page<VideoDto> videosDto = new PageImpl<>(Collections.singletonList(videoDto));

        when(videoService.getByTitleVideo("Java", pageable))
                .thenReturn(videosDto);

        var result = videoController.videoResponseEntityGetByTitle("Java", pageable);

        assertEquals(result, ResponseEntity.ok(videosDto));
    }
}