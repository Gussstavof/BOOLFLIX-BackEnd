package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import com.challenge.alura.AluraFlix.dto.mapper.Mapper;
import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
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
    @Mock
    Mapper mapper;

    Category categoryUpdate;
    Category category;
    CategoryDto categoryUpdateDto;
    CategoryDto categoryDto;
    Video video;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        categoryDto = CategoryDto.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        category = Category.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
        categoryUpdateDto = CategoryDto.builder()
                .id("1")
                .title("Front-End")
                .color("#00000B")
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
        when(mapper.toCategoryDto(category))
                .thenReturn(categoryDto);
        when(mapper.toCategory(categoryDto))
                .thenReturn(category);
        var result = categoryService.save(categoryDto);

        assertSame(result, categoryDto);
    }

    @Test
    void getAllVideosTest(){
        var categories = new PageImpl<>(Collections.singletonList(category));
        var categoriesDto = new PageImpl<>(Collections.singletonList(categoryDto));

        when(categoryRepository.findAll(pageable))
                .thenReturn(categories);
        when(mapper.toCategoryDto(categories.getContent()))
                .thenReturn(categoriesDto);

        var result = categoryService.getAll(pageable);

        assertEquals(result, categoriesDto);
    }

    @Test
    void getByIdCategoryTest(){
        when(categoryRepository.findById("1"))
                .thenReturn(ofNullable(category));
        when(mapper.toCategoryDto(category))
                .thenReturn(categoryDto);

        var result = categoryService.getById("1");

        assertSame(result, categoryDto);
    }

    @Test
    void updateCategoryTest(){
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(mapper.toCategoryDto(categoryUpdate))
                .thenReturn(categoryUpdateDto);
        when(categoryRepository.save(categoryUpdate))
                .thenReturn(categoryUpdate);

        var result = categoryService.update("1", categoryUpdateDto);

        assertInstanceOf(CategoryDto.class, result);
        assertEquals(categoryUpdate.getTitle(), result.getTitle());

    }

    @Test
    void deleteVideoTest(){
        doNothing()
                .when(categoryRepository)
                .deleteById("1");
        categoryService.deleteCategory("1");
        verify(categoryRepository).deleteById(category.getId());

    }

    @Test
    void getVideosByCategoryTest(){
       Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(videoRepository.findByCategory(category, pageable))
                .thenReturn(videos);

        var result = categoryService.getVideosByCategory("1", pageable);

        assertEquals(result, videos);
    }
}