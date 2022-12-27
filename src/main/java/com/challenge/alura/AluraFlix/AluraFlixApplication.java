package com.challenge.alura.AluraFlix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AluraFlixApplication {
	public static void main(String[] args) {
		SpringApplication.run(AluraFlixApplication.class, args);
	}
}
