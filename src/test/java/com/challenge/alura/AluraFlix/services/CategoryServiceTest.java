package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
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
import java.util.Set;

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

    Category category;
    Category categoryUpdate;
    Video video;

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
    void save() {
        when(categoryRepository.save(category))
                .thenReturn(category);

        var result = categoryService.save(category);

        assertSame(result, category);
    }

    @Test
    void getAll(){
        var categories = Collections.singletonList(category);
        when(categoryRepository.findAll())
                .thenReturn(categories);

        var result = categoryService.getAll();

        assertSame(result, categories);
    }

    @Test
    void getById(){
        when(categoryRepository.findById("1"))
                .thenReturn(ofNullable(category));

        var result = categoryService.getById("1");

        assertSame(result, category);
    }

    @Test
    void update(){
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(categoryRepository.save(categoryUpdate))
                .thenReturn(categoryUpdate);

        var result = categoryService.update("1", categoryUpdate);

        assertEquals(result, categoryUpdate);

    }

    @Test
    void delete(){
        doNothing()
                .when(categoryRepository)
                .deleteById("1");
        categoryService.deleteCategory("1");
        verify(categoryRepository).deleteById(category.getId());

    }

    @Test
    void getVideosByCategory(){
       Set<Video> videos = Collections.singleton(video);
        when(categoryRepository.findById("1"))
                .thenReturn(Optional.ofNullable(category));
        when(videoRepository.findByCategory(category)).thenReturn(videos);

        var result = categoryService.getVideosByCategory("1");

        assertEquals(result, videos);
    }
}