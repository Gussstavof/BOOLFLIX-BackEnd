package com.challenge.alura.AluraFlix.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "collection_category")
public class Category {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String color;

}
