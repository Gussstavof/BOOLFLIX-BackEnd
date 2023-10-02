package com.challenge.alura.AluraFlix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
@EnableMongoRepositories
public class AluraFlixApplication {
	public static void main(String[] args) {
		SpringApplication.run(AluraFlixApplication.class, args);
	}
}
