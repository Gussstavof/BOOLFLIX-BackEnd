package com.challenge.alura.AluraFlix.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@Builder
@Document(collection = "collection_video")
public class Video {
    @Id
    private  String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    @URL
    private String url;
}
