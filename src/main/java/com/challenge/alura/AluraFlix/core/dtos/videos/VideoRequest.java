package com.challenge.alura.AluraFlix.core.dtos.videos;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
