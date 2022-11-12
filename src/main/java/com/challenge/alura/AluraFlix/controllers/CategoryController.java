package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Category> saveCategoryResponseEntity(@Valid @RequestBody Category category, URI uri){
        return ResponseEntity.created(uri).body(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllResponseEntity(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getByIdResponseEntity(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateByIdCategoryResponseEntity(@Valid @RequestBody Category category
            ,@PathVariable String id){
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryResponseEntity(String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleted");
    }
}
