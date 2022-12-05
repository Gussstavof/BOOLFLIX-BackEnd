package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import com.challenge.alura.AluraFlix.dto.Mapper;
import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @InjectMocks
    CategoryController categoryController;
    @Mock
    CategoryService categoryService;

    Category category;
    CategoryDto categoryDto;
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
        categoryDto = CategoryDto.builder()
                .id("1")
                .title("Front-End")
                .color("#00008B")
                .build();
    }

    @Test
    void saveCategoryResponseEntityTest() {
        when(categoryService.save(categoryDto))
                .thenReturn(categoryDto);

        var result = categoryController
                .saveCategoryResponseEntity(categoryDto, location);

        assertEquals(result, ResponseEntity.created(location).body(categoryDto));
    }

    @Test
    void getAllCategoriesResponseEntityTest(){
        var categoriesDto = new PageImpl<>(Collections.singletonList(categoryDto));
        when(categoryService.getAll(pageable))
                .thenReturn(categoriesDto);

        var result = categoryController
                .getAllResponseEntity(pageable);

        assertEquals(result, ResponseEntity.ok(categoriesDto));
    }

    @Test
    void getByIdCategoryResponseEntityTest(){
        when(categoryService.getById("1"))
                .thenReturn(categoryDto);

        var result = categoryController
                .getByIdResponseEntity("1");

        assertEquals(result, ResponseEntity.ok(categoryDto));
    }

    @Test
    void updateByIdCategoryResponseEntityTest(){
        when(categoryService.update("1", categoryDto))
                .thenReturn(categoryDto);

        var result = categoryController
                .updateByIdCategoryResponseEntity(categoryDto,"1");

        assertEquals(result, ResponseEntity.ok(categoryDto));
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