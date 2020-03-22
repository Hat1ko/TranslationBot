package com.greenboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TranslationApplication {
	public static void main(String[] args) {
		SpringApplication.run(TranslationApplication.class, args);
	}
}
