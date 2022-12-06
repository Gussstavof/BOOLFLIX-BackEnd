package com.challenge.alura.AluraFlix.dto;

import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class Mapper {

    public Category toCategory(CategoryDto categoryDto){
        return (Category) Stream.of(categoryDto).map(Category::new);
    }

    public CategoryDto toCategoryDto(Category category){
        return new CategoryDto(category.getId(), category.getTitle(), category.getColor());
    }

    public Page<CategoryDto> toCategoryDto(List<Category> categories) {
        return new PageImpl<>(categories.stream().map(CategoryDto::new).collect(Collectors.toList()));
    }

    public Video toVideo(VideoDto videoDto){
        return (Video) Stream.of(videoDto).map(Video::new);
    }

    public VideoDto toVideoDto(Video video){
        return (VideoDto) Stream.of(video).map(VideoDto::new);
    }

    public Page<VideoDto> toVideoDto(Collection<Video> videos){
        return new PageImpl<>(videos.stream().map(VideoDto::new).collect(Collectors.toList()));
    }

}
