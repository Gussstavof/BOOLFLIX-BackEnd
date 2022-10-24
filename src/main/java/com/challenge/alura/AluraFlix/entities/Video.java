package com.challenge.alura.AluraFlix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "collection_video")
public class Video {

    @MongoId
    private Long id;
    private String title;
    private String description;
    private String url;

}
