package com.challenge.alura.AluraFlix.core.entities.videos;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;


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
