package com.challenge.alura.AluraFlix.entities;

import com.challenge.alura.AluraFlix.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@AllArgsConstructor
@Builder
@Document(collection = "collection_video")
public class Video {

    private String title;
    private String description;
    private String url;
    private StatusEnum status;

}
