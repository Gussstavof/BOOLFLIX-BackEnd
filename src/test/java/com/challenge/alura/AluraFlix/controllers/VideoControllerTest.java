package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.entities.dto.VideoDto;
import com.challenge.alura.AluraFlix.enums.StatusEnum;
import com.challenge.alura.AluraFlix.services.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
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

    VideoDto videoDto;
    VideoDto videoDtoNull;

    @BeforeEach
    void setUp(){
        videoDto = VideoDto.builder()
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoDtoNull =  VideoDto.builder()
                .title(null)
                .description(null)
                .url(null)
                .build();
    }

    @Test
    void save_video_created() throws Exception {

        mockMvc.perform(post("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void attributes_null_and_return_400() throws Exception {
        mockMvc.perform(post("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoDtoNull)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}