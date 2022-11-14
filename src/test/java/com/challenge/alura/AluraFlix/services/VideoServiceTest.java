package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
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

    @Mock
    CategoryRepository categoryRepository;

    Video video;
    Video videoUpdate;
    Category category;
    Category categoryId;

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
    void saveVideoTest() {
        when(videoRepository.save(any())).thenReturn(video);
        when(categoryRepository.findById("1")).thenReturn(Optional.of(category));

        Video videoResponse = videoService.saveVideo(video);

        assertSame(videoResponse, video);
    }

    @Test
    void  getAllTest(){
        var videos = Collections.singletonList(video);

        when(videoRepository.findAll())
                .thenReturn(videos);

        var videosResponse = videoService.getAllVideos();

        assertSame(videosResponse, videos);
    }

    @Test
    void  getVideoByIdTest(){
       when(videoRepository.findById("1"))
               .thenReturn(java.util.Optional.ofNullable(video));

       var videoResponse = videoService.getByIdVideo("1");

       assertSame(videoResponse, video);
    }

    @Test
    void  getVideoByIdNotFoundTest(){
        when(videoRepository.findById("0"))
                .thenThrow(new ExceptionNotFound("Id not found"));

        assertThrows(ExceptionNotFound.class, () -> videoService.getByIdVideo("0"));
    }

    @Test
    void updateVideoTest(){
        when(videoRepository.findById("1"))
                .thenReturn(Optional.of(video));
        when(videoRepository.save(videoUpdate))
                .thenReturn(videoUpdate);

        var result = videoService.updateVideo("1", videoUpdate);

        assertSame(result, videoUpdate);
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
        var videos = Collections.singleton(video);
        when(videoRepository.findByTitleContains("Java")).thenReturn(videos);

        var result = videoService.getByTitleVideo("Java");

        assertEquals(result, videos);
    }
}