package com.challenge.alura.AluraFlix.entities.dto;

import com.challenge.alura.AluraFlix.entities.Video;
import com.challenge.alura.AluraFlix.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VideoDto {

    @NotEmpty
    private String title;
    @NotEmpty
    @NotNull
    private String description;
    @URL
    @NotEmpty
    private String url;
    private StatusEnum status;

    public VideoDto(Video video) {
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.url = video.getUrl();
        this.status = video.getStatus();
    }

}
