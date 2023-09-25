package com.challenge.alura.AluraFlix.core.entities.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Component
public class CategoryRequest {
        private String id;
        private String title;
        private String color;

        public Category toCategory() {
                Category category = new Category();
                BeanUtils.copyProperties(this, category);
                return category;
        }
}
