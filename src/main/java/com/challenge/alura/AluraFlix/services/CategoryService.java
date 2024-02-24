package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dtos.categories.CategoryRequest;
import com.challenge.alura.AluraFlix.entities.categories.Category;
import com.challenge.alura.AluraFlix.dtos.categories.CategoryResponse;
import com.challenge.alura.AluraFlix.entities.videos.Video;
import com.challenge.alura.AluraFlix.exception.NotFoundException;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        return new CategoryResponse(
                getIdOrThrow(id).orElseThrow(() -> new NotFoundException("category doesn't exist"))
        );
    }

    public CategoryResponse update(String id, CategoryRequest categoryRequest) {
        return categoryRepository.findById(id).map(categoryUpdate -> {
            categoryUpdate.setTitle(categoryRequest.getTitle());
            categoryUpdate.setColor(categoryRequest.getColor());
            var category = categoryRepository.save(categoryUpdate);
            return new CategoryResponse(category);
        }).orElseThrow(() -> new NotFoundException("id not found"));
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Page<Video> getVideosByCategory(String id, Pageable pageable) {
        return videoRepository.findAllByCategory(
                getIdOrThrow(id).orElseThrow(() -> new NotFoundException("category doesn't exist")),
                pageable
        );
    }

    private Optional<Category> getIdOrThrow(String id) {
        return categoryRepository.findById(id);
    }
}
