package com.challenge.alura.AluraFlix.core.dtos.videos;

import com.challenge.alura.AluraFlix.core.entities.categories.Category;
import com.challenge.alura.AluraFlix.core.entities.videos.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoResponse {
    private  String id;
    private String title;
    private String description;
    private Category category;
    private String url;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.category = video.getCategory();
        this.url = video.getUrl();
    }
}
