package com.challenge.alura.AluraFlix.dto.mapper;

import com.challenge.alura.AluraFlix.dto.CategoryDto;
import com.challenge.alura.AluraFlix.dto.VideoDto;
import com.challenge.alura.AluraFlix.entities.Category;
import com.challenge.alura.AluraFlix.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Mapper {

    public Category toCategory(CategoryDto categoryDto){
        return new Category(categoryDto);
    }

    public CategoryDto toCategoryDto(Category category){
        return new CategoryDto(category);
    }

    public Page<CategoryDto> toCategoryDto(List<Category> categories) {
        return new PageImpl<>(categories.stream().map(CategoryDto::new).collect(Collectors.toList()));
    }

    public Video toVideo(VideoDto videoDto){
        return new Video(videoDto);
    }

    public VideoDto toVideoDto(Video video){
        return new VideoDto(video);
    }

    public Page<VideoDto> toVideoDto(Collection<Video> videos){
        return new PageImpl<>(videos.stream().map(VideoDto::new).collect(Collectors.toList()));
    }

}
