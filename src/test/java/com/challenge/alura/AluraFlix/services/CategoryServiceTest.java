package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import com.challenge.alura.AluraFlix.dto.Mapper;
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
import java.util.stream.Collectors;

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

    Category category;
    Category categoryUpdate;
    Video video;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        category = Category.builder()
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
        var categoryDto = mapper.toCategorySaveDto(category);
        var result = categoryService.save(categoryDto);

        assertSame(result, categoryDto);
    }

    @Test
    void getAllVideosTest(){
        var categories = new PageImpl<>(Collections.singletonList(category));
        var categoriesDto = new PageImpl<>(categories.stream().map(CategoryDto::new).collect(Collectors.toList()));
        when(categoryRepository.findAll(pageable))
                .thenReturn(categories);
        when(mapper.toCategorySaveDto(categories.getContent())).thenReturn(categoriesDto);
        var result = categoryService.getAll(pageable);

        assertEquals(result, categoriesDto);
    }

    @Test
    void getByIdCategoryTest(){
        var categoryDto = mapper.toCategorySaveDto(category);
        when(categoryRepository.findById("1"))
                .thenReturn(ofNullable(category));
        when(mapper.toCategorySaveDto(category))
                .thenReturn(categoryDto);

        var result = categoryService.getById("1");

        assertSame(result, categoryDto);
    }

    @Test
    void updateCategoryTest(){
        var categoryDto = new Mapper().toCategorySaveDto(categoryUpdate);
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(categoryRepository.save(categoryUpdate))
                .thenReturn(categoryUpdate);
        when(mapper.toCategorySaveDto(categoryUpdate)).thenReturn(categoryDto);

        var result = categoryService.update("1", categoryDto);

        assertInstanceOf(CategoryDto.class, result);
        assertEquals(categoryDto.getTitle(), result.getTitle());

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