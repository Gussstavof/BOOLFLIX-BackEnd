package com.challenge.alura.AluraFlix.entities;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "collection_category")
public class Category {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String color;

    public Category(CategoryDto categoryDto) {
        this.id = categoryDto.getId();
        this.title = categoryDto.getTitle();
        this.color = categoryDto.getColor();
    }
}
