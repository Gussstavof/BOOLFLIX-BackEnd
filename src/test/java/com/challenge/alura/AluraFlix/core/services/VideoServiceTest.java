package com.challenge.alura.AluraFlix.core.services;

import com.challenge.alura.AluraFlix.dtos.videos.VideoRequest;
import com.challenge.alura.AluraFlix.entities.categories.Category;
import com.challenge.alura.AluraFlix.entities.videos.Video;
import com.challenge.alura.AluraFlix.dtos.videos.VideoResponse;
import com.challenge.alura.AluraFlix.exception.NotFoundException;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import com.challenge.alura.AluraFlix.services.VideoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    @Mock
    CategoryRepository categoryRepository;

    Video video;
    Video videoUpdate;
    VideoRequest videoRequest;
    VideoRequest videoUpdateRequest;
    VideoResponse videoUpdateResponse;
    VideoResponse videoResponse;
    Category category;
    Category categoryId;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        categoryId = Category.builder()
                .id("1")
                .build();

        category = Category.builder()
                .id("1")
                .title("Back-end")
                .color("#00000")
                .build();

        videoRequest = VideoRequest.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .category(categoryId)
                .url("https://www.youtube.com/watch/teste")
                .build();

        videoResponse = VideoResponse.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .url("https://www.youtube.com/embed/teste")
                .category(category)
                .build();

        videoUpdateRequest = VideoRequest.builder()
                .id("1")
                .title("testando2")
                .category(categoryId)
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        videoUpdateResponse = VideoResponse.builder()
                .id("1")
                .title("testando2")
                .category(categoryId)
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();

        video = Video.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .category(categoryId)
                .url("https://www.youtube.com/")
                .build();

        videoUpdate = Video.builder()
                .id("1")
                .title("testando2")
                .category(categoryId)
                .description("testandoController")
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void saveVideoAndVerifyUrlFormatTest() {
        Mockito.when(videoRepository.save(Mockito.any(Video.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Mockito.when(categoryRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(category));

        var result = videoService.saveVideo(videoRequest);

        Assertions.assertEquals("https://www.youtube.com/embed/teste", result.getUrl());
        Assertions.assertEquals(videoResponse, result);
        Assertions.assertEquals(videoResponse.getCategory(), result.getCategory());
        verify(videoRepository, times(1)).save(Mockito.any(Video.class));
    }

    @Test
    void saveVideoTest() {
        when(videoRepository.save(any()))
                .thenReturn(video);
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.of(category));

        VideoResponse result = videoService.saveVideo(videoRequest);

        assertEquals(new VideoResponse(video), result);
    }

    @Test
    void  getAllTest(){
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        Page<VideoResponse> videosDto = new PageImpl<>(
                Collections.singletonList(
                    new VideoResponse(video)
                )
        );

        when(videoRepository.findAll(pageable))
                .thenReturn(videos);

        var result = videoService.getAllVideos(pageable);

        assertEquals(videosDto, result);
    }

    @Test
    void  getVideoByIdTest(){
       when(videoRepository.findById("1"))
               .thenReturn(java.util.Optional.ofNullable(video));

       var result = videoService.getByIdVideo("1");

       assertEquals(new VideoResponse(video), result);
    }

    @Test
    void  getVideoByIdNotFoundTest(){
        when(videoRepository.findById("0"))
                .thenThrow(new NotFoundException("Id not found"));

        assertThrows(NotFoundException.class, () -> videoService.getByIdVideo("0"));
    }

    @Test
    void updateVideoTest(){
        when(videoRepository.findById("1"))
                .thenReturn(Optional.of(video));
        when(videoRepository.save(videoUpdate))
                .thenReturn(videoUpdate);

        var result = videoService.updateVideo("1", videoUpdateRequest);

        assertEquals(videoUpdateResponse, result);
    }


    @Test
    void deleteVideoTest(){

        when(videoRepository.findById("1"))
                .thenReturn(Optional.ofNullable(video));

        doNothing()
                .when(videoRepository)
                .deleteById("1");

        videoService.deleteVideo("1");
        verify(videoRepository).deleteById(video.getId());
    }

    @Test
    void getVideoByTitleTest(){
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        Page<VideoResponse> videosDto = new PageImpl<>(
                Collections.singletonList(
                        new VideoResponse(video)
                )
        );

        when(videoRepository.findByTitleContains("Java",pageable))
                .thenReturn(videos);

        var result = videoService.getByTitleVideo("Java",pageable);

        assertEquals(videosDto, result);
    }
}