package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    URI location;

    @BeforeEach
    void setUp() {
        this.category = Category.builder()
                .id("1")
                .title("Back-End")
                .color("#00008B")
                .build();
    }

    @Test
    void saveCategoryResponseEntity() {
        when(categoryService.save(category)).thenReturn(category);

        var result = categoryController.saveCategoryResponseEntity(category, location);

        assertEquals(result, ResponseEntity.created(location).body(category));
    }

    @Test
    void getAllCategoriesResponseEntity(){
        var categories = Collections.singletonList(category);
        when(categoryService.getAll()).thenReturn(categories);

        var result = categoryController.getAllResponseEntity();

        assertEquals(result, ResponseEntity.ok(categories));
    }

    @Test
    void getByIdCategoryResponseEntity(){
        when(categoryService.getById("1")).thenReturn(category);

        var result = categoryController.getByIdResponseEntity("1");

        assertEquals(result, ResponseEntity.ok(category));
    }
}