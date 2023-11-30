package com.challenge.alura.AluraFlix.dtos.categories;

import com.challenge.alura.AluraFlix.entities.categories.Category;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Builder
public class CategoryResponse {
        private String id;
        private String title;
        private String color;

        public CategoryResponse(Category category) {
                this.id = category.getId();
                this.title = category.getTitle();
                this.color = category.getColor();
        }
}
