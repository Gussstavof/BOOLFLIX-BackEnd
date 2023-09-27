package com.challenge.alura.AluraFlix.core.dtos.videos;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoRequest {
    private  String id;
    private String title;
    private String description;
    private Category category;
    private String url;

    public Video toVideo() {
        Video video = new Video();
        BeanUtils.copyProperties(this, video);
        return video;
    }
}
