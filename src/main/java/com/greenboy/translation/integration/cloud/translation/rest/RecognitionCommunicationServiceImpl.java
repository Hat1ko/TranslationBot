package com.greenboy.translation.integration.cloud.translation.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenboy.translation.integration.cloud.translation.builder.HttpHeadersBuilder;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.translation.integration.properties.CloudCommunicationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecognitionCommunicationServiceImpl implements RecognitionCommunicationService {

	private final RestTemplate recognitionRest;
	private final CloudCommunicationProperties cloudTranslationProperties;
	private final HttpHeadersBuilder httpHeadersBuilder;
	private final ObjectMapper objectMapper;

	@Override
	public RecognitionResponse recognizeText(RecognitionRequest request) throws JsonProcessingException {

		log.info("POST for recognition | content : {}", request.getContent());

		HttpEntity<RecognitionRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		RecognitionResponse response = recognitionRest.exchange(cloudTranslationProperties.getRecognitionUri(),
				HttpMethod.POST, requestEntity, RecognitionResponse.class).getBody();

//		RecognitionResponse response = recognitionRest.postForEntity(cloudTranslationProperties.getRecognitionUri(),
//				request, RecognitionResponse.class).getBody();

		log.info("Response for recognition | body : {}", objectMapper.writeValueAsString(response));

		return response;
	}
}
