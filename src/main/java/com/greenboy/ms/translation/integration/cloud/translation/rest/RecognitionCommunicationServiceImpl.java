package com.greenboy.ms.translation.integration.cloud.translation.rest;

import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.greenboy.ms.translation.builder.HttpHeadersBuilder;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.ms.translation.integration.properties.CloudCommunicationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class RecognitionCommunicationServiceImpl implements RecognitionCommunicationService {

	private final RestTemplate recognitionRest;
	private final CloudCommunicationProperties cloudTranslationProperties;
	private final HttpHeadersBuilder httpHeadersBuilder;

	@Override
	public RecognitionResponse recognizeText(RecognitionRequest request) {

		log.info("POST for recognition | content : {}", request.getContent());

		HttpEntity<RecognitionRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		RecognitionResponse response = recognitionRest.exchange(cloudTranslationProperties.getRecognitionUri(),
				HttpMethod.POST, requestEntity, RecognitionResponse.class).getBody();

		log.info("Response for recognition | languages : {}", response.getLanguages().stream()
				.map(e -> String.format("languageCode : %s, confidence : %s", e.getLanguageCode(), e.getConfidence()))
				.collect(Collectors.joining(" ")));

		return response;
	}
}
