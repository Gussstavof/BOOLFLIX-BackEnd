package com.challenge.alura.AluraFlix.dto;

import com.challenge.alura.AluraFlix.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class Mapper {

    public Category toCategory(CategoryDto categoryDto){
        return (Category) Stream.of(categoryDto).map(Category::new);
    }

    public CategoryDto toCategorySaveDto(Category category){
        return new CategoryDto(category.getId(), category.getTitle(), category.getColor());
    }

    public Page<CategoryDto> toCategorySaveDto(List<Category> categories) {
        return new PageImpl<>(categories.stream().map(CategoryDto::new).collect(Collectors.toList()));
    }

}
