package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import com.challenge.alura.AluraFlix.dto.Mapper;
import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.exception.ExceptionNotFound;
import com.challenge.alura.AluraFlix.repositories.CategoryRepository;
import com.challenge.alura.AluraFlix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private Mapper mapper;

    public CategoryDto save(CategoryDto categoryDto){
        var category = categoryRepository.save(mapper.toCategory(categoryDto));
        return mapper.toCategoryDto(category);
    }

    public Page<CategoryDto> getAll(Pageable pageable) {
        var page =  categoryRepository.findAll(pageable);
        return mapper.toCategoryDto(page.getContent());
    }

    public CategoryDto getById(String id) {
        return mapper.toCategoryDto(getIdOrThrow(id));
    }

    public CategoryDto update(String id, CategoryDto categoryDto) {
        return categoryRepository.findById(id).map(categoryUpdate -> {
           categoryUpdate.setTitle(categoryDto.getTitle());
           categoryUpdate.setColor(categoryDto.getColor());
           return mapper.toCategoryDto(categoryRepository.save(categoryUpdate));
        }).orElseThrow(() -> new ExceptionNotFound("id not found"));
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Page<Video> getVideosByCategory(String id, Pageable pageable){
        return videoRepository.findByCategory(getIdOrThrow(id), pageable);
    }


    private Category getIdOrThrow(String id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Id not found"));
    }

}
