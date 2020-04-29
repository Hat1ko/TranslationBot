package com.greenboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
public class TranslationApplication {
	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TranslationApplication.class, args);
	}
}
