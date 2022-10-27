package com.challenge.alura.AluraFlix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Document(collection = "collection_video")
public class Video {

    @Id
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    @NotNull
    private String description;
    @URL
    @NotEmpty
    private String url;

}
