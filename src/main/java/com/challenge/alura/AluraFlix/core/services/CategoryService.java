package com.challenge.alura.AluraFlix.core.services;

import com.challenge.alura.AluraFlix.core.entities.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.categories.CategoryResponse;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import com.challenge.alura.AluraFlix.core.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.core.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.core.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private VideoRepository videoRepository;

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryRequest.toCategory());

        return new CategoryResponse(category);
    }

    public Page<CategoryResponse> getAll(Pageable pageable) {
        return new PageImpl<>(
                categoryRepository.findAll(pageable)
                        .stream()
                        .map(CategoryResponse::new)
                        .collect(Collectors.toList())
        );
    }

    public CategoryResponse getById(String id) {
        return new CategoryResponse(getIdOrThrow(id));
    }

    public CategoryResponse update(String id, CategoryRequest categoryRequest) {
        return categoryRepository.findById(id).map(categoryUpdate -> {
            categoryUpdate.setTitle(categoryRequest.getTitle());
            categoryUpdate.setColor(categoryRequest.getColor());
            var category = categoryRepository.save(categoryUpdate);
            return new CategoryResponse(category);
        }).orElseThrow(() -> new ExceptionNotFound("id not found"));
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Page<Video> getVideosByCategory(String id, Pageable pageable) {
        return videoRepository.findByCategory(getIdOrThrow(id), pageable);
    }

    private Category getIdOrThrow(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }
}
