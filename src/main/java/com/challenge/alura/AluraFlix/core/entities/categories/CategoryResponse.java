package com.challenge.alura.AluraFlix.core.entities.categories;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
