package com.hatiko.ms.translation.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.hatiko.ms.translation.integration.properties.CloudCommunicationProperties;
import com.hatiko.ms.translation.integration.text.to.speech.properties.TextToSpeechProperties;

@Configuration
public class GoogleTranslationApiConfiguration {

	@Bean(name = { "restTemplateBuilder" })
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}

	@Bean(name = { "translationRest" })
	public RestTemplate googleTranslationRest(RestTemplateBuilder restTemplateBuilder,
			CloudCommunicationProperties cloudTranslationProperties) {

		return restTemplateBuilder.rootUri(cloudTranslationProperties.getTranslationUri())
				.setConnectTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getConnect()))
				.setReadTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getRead())).build();
	}

	@Bean(name = { "recognitionRest" })
	public RestTemplate googleRecognitionRest(RestTemplateBuilder restTemplateBuilder,
			CloudCommunicationProperties cloudTranslationProperties) {

		return restTemplateBuilder.rootUri(cloudTranslationProperties.getRecognitionUri())
				.setConnectTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getConnect()))
				.setReadTimeout(Duration.ofMillis(cloudTranslationProperties.getTimeout().getRead())).build();
	}

	@Bean(name = {"synthesizerRest"})
	public RestTemplate googleSynthesizerRest(RestTemplateBuilder restTemplateBuilder,
											  TextToSpeechProperties textToSpeechProperties) {

		return restTemplateBuilder.rootUri(textToSpeechProperties.getUri())
				.setConnectTimeout(Duration.ofMillis(textToSpeechProperties.getTimeout().getConnect()))
				.setReadTimeout(Duration.ofMillis(textToSpeechProperties.getTimeout().getRead())).build();
	}
}
