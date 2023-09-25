package com.challenge.alura.AluraFlix.core.entities.videos;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "collection_video")
public class Video{
    @Id
    private  String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Category category;
    @NotBlank
    @URL
    private String url;
}
