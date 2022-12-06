package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dto.Mapper;
import com.challenge.alura.AluraFlix.dto.VideoDto;
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
    @Mock
    Mapper mapper;

    Video video;
    Video videoUpdate;
    VideoDto videoDto;
    VideoDto videoUpdateDto;
    Category category;
    Category categoryId;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        categoryId = Category.builder()
                .id("1")
                .build();

        videoDto = VideoDto.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .category(categoryId)
                .url("https://www.youtube.com/")
                .build();

        videoUpdateDto = VideoDto.builder()
                .id("1")
                .title("testando2")
                .category(categoryId)
                .description("testandoController")
                .url("https://www.youtube.com/")
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
        when(videoRepository.save(video))
                .thenReturn(video);
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.of(category));
        when(mapper.toVideo(videoDto))
                .thenReturn(video);

        VideoDto videoResponse = videoService.saveVideo(videoDto);

        assertSame(videoResponse, videoDto);
    }

    @Test
    void  getAllTest(){
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        Page<VideoDto> videosDto = new PageImpl<>(Collections.singletonList(videoDto));

        when(videoRepository.findAll(pageable))
                .thenReturn(videos);
        when(mapper.toVideoDto(videos.getContent()))
                .thenReturn(videosDto);

        var videosResponse = videoService.getAllVideos(pageable);

        assertSame(videosResponse, videosDto);
    }

    @Test
    void  getVideoByIdTest(){
       when(videoRepository.findById("1"))
               .thenReturn(java.util.Optional.ofNullable(video));
       when(mapper.toVideoDto(video))
               .thenReturn(videoDto);

       var videoResponse = videoService.getByIdVideo("1");

       assertSame(videoResponse, videoDto);
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
        when(mapper.toVideoDto(videoUpdate))
                .thenReturn(videoUpdateDto);

        var result = videoService.updateVideo("1", videoUpdateDto);

        assertSame(result, videoUpdateDto);
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
        Page<VideoDto> videosDto = new PageImpl<>(Collections.singletonList(videoDto));

        when(videoRepository.findByTitleContains("Java",pageable))
                .thenReturn(videos);
        when(mapper.toVideoDto(videos.getContent()))
                .thenReturn(videosDto);

        var result = videoService.getByTitleVideo("Java",pageable);

        assertEquals(result, videosDto);
    }
}