package com.challenge.alura.AluraFlix.dtos.videos;

import com.challenge.alura.AluraFlix.entities.categories.Category;
import com.challenge.alura.AluraFlix.entities.videos.Video;
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
