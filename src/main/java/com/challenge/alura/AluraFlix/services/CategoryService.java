package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VideoRepository videoRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("id not found"));
    }

    public Category update(String id, Category category) {
        return categoryRepository.findById(id).map(categoryUpdate -> {
           categoryUpdate.setTitle(category.getTitle());
           categoryUpdate.setColor(category.getColor());
           return categoryRepository.save(categoryUpdate);
        }).orElseThrow(() -> new ExceptionNotFound("id not found"));
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Set<Video> getVideosByCategory(String id){
        return videoRepository.findByCategory(getIdOrThrow(id));
    }


    private Category getIdOrThrow(String id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

}
