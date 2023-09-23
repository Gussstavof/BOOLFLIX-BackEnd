package com.challenge.alura.AluraFlix.core.entities.categories;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Component
public class CategoryDto {
        @Id
        private String id;
        @NotBlank
        private String title;
        @NotBlank
        private String color;

        public CategoryDto(Category category) {
                this.id = category.getId();
                this.title = category.getTitle();
                this.color = category.getColor();
        }
}
