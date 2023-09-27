package com.challenge.alura.AluraFlix.core.controllers;

import com.challenge.alura.AluraFlix.core.dtos.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.dtos.categories.CategoryResponse;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import com.challenge.alura.AluraFlix.core.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @InjectMocks
    CategoryController categoryController;
    @Mock
    CategoryService categoryService;

    Category category;
    CategoryRequest categoryRequest;
    CategoryResponse categoryResponse;
    Video video;
    URI location;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        video = Video.builder()
                .id("1")
                .title("testando")
                .description("testandoController")
                .category(category)
                .url("https://www.youtube.com/")
                .build();
        categoryRequest = CategoryRequest.builder()
                .id("1")
                .title("Front-End")
                .color("#00008B")
                .build();
        categoryResponse = CategoryResponse.builder()
                .id("1")
                .title("Front-End")
                .build();
    }

    @Test
    void saveCategoryResponseEntityTest() {
        when(categoryService.save(categoryRequest))
                .thenReturn(categoryResponse);

        var result = categoryController
                .saveCategoryResponseEntity(categoryRequest, location);

        assertEquals(result, ResponseEntity.created(location).body(categoryResponse));
    }

    @Test
    void getAllCategoriesResponseEntityTest(){
        var categoriesDto = new PageImpl<>(Collections.singletonList(categoryResponse));
        when(categoryService.getAll(pageable))
                .thenReturn(categoriesDto);

        var result = categoryController
                .getAllResponseEntity(pageable);

        assertEquals(result, ResponseEntity.ok(categoriesDto));
    }

    @Test
    void getByIdCategoryResponseEntityTest(){
        when(categoryService.getById("1"))
                .thenReturn(categoryResponse);

        var result = categoryController
                .getByIdResponseEntity("1");

        assertEquals(result, ResponseEntity.ok(categoryResponse));
    }

    @Test
    void updateByIdCategoryResponseEntityTest(){
        when(categoryService.update("1", categoryRequest))
                .thenReturn(categoryResponse);

        var result = categoryController
                .updateByIdCategoryResponseEntity(categoryRequest,"1");

        assertEquals(result, ResponseEntity.ok(categoryResponse));
    }

    @Test
    void deleteCategoryResponseEntityTest(){
        doNothing().when(categoryService)
                .deleteCategory("1");

        var result = categoryController.deleteCategoryResponseEntity("1");

        assertEquals(result, ResponseEntity.ok("deleted"));
    }

    @Test
    void getVideosByCategoryResponseEntityTest(){
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        when(categoryService.getVideosByCategory("1", pageable))
                .thenReturn(videos);

        var result = categoryController
                .getVideosByCategoryResponseEntity("1", pageable);

        assertEquals(result, ResponseEntity.ok(videos));
    }
}