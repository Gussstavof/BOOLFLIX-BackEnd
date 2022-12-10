package com.challenge.alura.AluraFlix.entities;

import com.challenge.alura.AluraFlix.dto.VideoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


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

    public Video(VideoDto videoDto) {
        this.id = videoDto.getId();
        this.title = videoDto.getTitle();
        this.description = videoDto.getDescription();
        this.category = videoDto.getCategory();
        this.url = videoDto.getUrl();
    }
}
