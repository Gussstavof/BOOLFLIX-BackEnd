package com.challenge.alura.AluraFlix.dto;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class VideoDto {
    @NotBlank
    private  String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Category category;
    @NotBlank
    @URL
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.category = video.getCategory();
        this.url = video.getUrl();
    }
}
