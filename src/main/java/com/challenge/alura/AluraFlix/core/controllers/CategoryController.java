package com.challenge.alura.AluraFlix.core.controllers;

import com.challenge.alura.AluraFlix.core.dtos.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.core.dtos.categories.CategoryResponse;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import com.challenge.alura.AluraFlix.core.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<CategoryResponse> saveCategoryResponseEntity(@Valid @RequestBody CategoryRequest category,
                                                                       URI uri){
        return ResponseEntity.created(uri).body(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllResponseEntity(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByIdResponseEntity(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/{id}/videos")
    @Cacheable(value = "get_by_category")
    public ResponseEntity<Page<Video>> getVideosByCategoryResponseEntity(@PathVariable String id,
                                                                         @PageableDefault Pageable pageable){
        return ResponseEntity.ok(categoryService.getVideosByCategory(id, pageable));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<CategoryResponse> updateByIdCategoryResponseEntity(@Valid @RequestBody CategoryRequest categoryRequest,
                                                                            @PathVariable String id){
        return ResponseEntity.ok(categoryService.update(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<String> deleteCategoryResponseEntity(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleted");
    }
}
