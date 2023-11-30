package com.challenge.alura.AluraFlix.entities.categories;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "collection_category")
public class Category{
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String color;
}
