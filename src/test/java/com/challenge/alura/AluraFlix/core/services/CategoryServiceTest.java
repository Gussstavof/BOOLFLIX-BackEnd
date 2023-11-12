package com.challenge.alura.AluraFlix.core.services;

import com.challenge.alura.AluraFlix.dtos.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.dtos.categories.CategoryResponse;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
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

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @InjectMocks
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    VideoRepository videoRepository;

    Category categoryUpdate;
    Category category;
    CategoryResponse categoryResponse;
    CategoryRequest categoryRequest;
    Video video;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        categoryRequest = CategoryRequest.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        category = Category.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        categoryResponse = CategoryResponse.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        categoryUpdate = Category.builder()
                .id("1")
                .title("Front-End")
                .color("#00000B")
                .build();
        video = Video.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .category(category)
                .url("https://www.youtube.com/")
                .build();
    }

    @Test
    void saveCategoryTest() {
        when(categoryRepository.save(category))
                .thenReturn(category);

        CategoryResponse result = categoryService.save(categoryRequest);

        assertEquals(categoryResponse, result);
    }

    @Test
    void getAllVideosTest() {
        var categories = new PageImpl<>(Collections.singletonList(category));
        var categoriesDto = new PageImpl<>(Collections.singletonList(categoryResponse));

        when(categoryRepository.findAll(pageable))
                .thenReturn(categories);

        var result = categoryService.getAll(pageable);

        assertEquals(result, categoriesDto);
    }

    @Test
    void getByIdCategoryTest() {
        when(categoryRepository.findById("1"))
                .thenReturn(ofNullable(category));

        var result = categoryService.getById("1");

        assertEquals(result, categoryResponse);
    }

    @Test
    void updateCategoryTest() {
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(categoryRepository.save(any()))
                .thenReturn(categoryUpdate);

        var result = categoryService.update("1", categoryRequest);

        assertInstanceOf(CategoryResponse.class, result);
        assertEquals(new CategoryResponse(categoryUpdate), result);
    }

    @Test
    void deleteVideoTest() {
        doNothing()
                .when(categoryRepository)
                .deleteById("1");
        categoryService.deleteCategory("1");
        verify(categoryRepository).deleteById(category.getId());
    }

    @Test
    void getVideosByCategoryTest() {
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(videoRepository.findByCategory(category, pageable))
                .thenReturn(videos);

        var result = categoryService.getVideosByCategory("1", pageable);

        assertEquals(result, videos);
    }
}