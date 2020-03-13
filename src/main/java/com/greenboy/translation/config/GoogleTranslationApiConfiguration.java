package com.greenboy.translation.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.greenboy.translation.properties.CloudTranslationProperties;

@Configuration
public class GoogleTranslationApiConfiguration {

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}
	
	@Bean(name = {"translationApi"})
	public RestTemplate googleTranslationApi(RestTemplateBuilder restTemplateBuilder,
			CloudTranslationProperties cloudTranslationProperties) {
		
		return  restTemplateBuilder.rootUri(cloudTranslationProperties.getUri())
				.setConnectTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getConnect()))
				.setReadTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getRead()))
				.build();
	}
}
