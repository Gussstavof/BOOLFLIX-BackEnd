package com.challenge.alura.AluraFlix.repositories;

import com.challenge.alura.AluraFlix.entities.categories.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository <Category, String> {

}
