package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VideoController.class)
@AutoConfigureMockMvc
class VideoControllerTest {

    @Autowired
    private VideoController videoController;

    @MockBean
    private VideoService videoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    Video video;

    @BeforeEach
    void setUp() {
        this.video = new Video(1L,
                "testando",
                "testandoController",
                "https://www.youtube.com/");
    }

    @Test
    void save_video_created() throws Exception {
        when(videoService.saveVideo(video))
                .thenReturn(video);

        mockMvc.perform(post("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(video)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}