package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<Category> saveCategoryResponseEntity(@Valid @RequestBody Category category, URI uri){
        return ResponseEntity.created(uri).body(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getAllResponseEntity(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getByIdResponseEntity(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/{id}/videos")
    @Cacheable(value = "get_by_category")
    public ResponseEntity<Set<Video>> getVideosByCategoryResponseEntity(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getVideosByCategory(id));
    }


    @PutMapping("/{id}")
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<Category> updateByIdCategoryResponseEntity(@Valid @RequestBody Category category
            ,@PathVariable String id){
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "get_by_category", allEntries = true)
    public ResponseEntity<String> deleteCategoryResponseEntity(String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleted");
    }
}
