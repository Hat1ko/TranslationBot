package com.greenboy.translation.integration.cloud.translation.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenboy.translation.integration.cloud.translation.builder.HttpHeadersBuilder;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.greenboy.translation.integration.cloud.translation.properties.CloudTranslationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TranslationCommunicationServiceImpl implements TranslationCommunicationService {

	private final RestTemplate translationRest;
	private final CloudTranslationProperties cloudTranslationProperties;
	private final HttpHeadersBuilder httpHeadersBuilder;
	private final ObjectMapper objectMapper;

	@Override
	public TranslationResponse translateText(TranslationRequest request) throws JsonProcessingException {

		log.info("POST to translate text | from : {}, to : {}, contents : {} ", request.getSourceLanguageCode(),
				request.getTargetLanguageCode(), objectMapper.writeValueAsString(request.getContents()));

		HttpEntity<TranslationRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		TranslationResponse response = translationRest.exchange(cloudTranslationProperties.getTranslationUri(),
				HttpMethod.POST, requestEntity, TranslationResponse.class).getBody();

//		TranslationResponse repsponse

//		TranslationResponse response = translationRest
//				.postForEntity(cloudTranslationProperties.getTranslationUri(), request, TranslationResponse.class)
//				.getBody();

		log.info("Response for translation | body : {}", objectMapper.writeValueAsString(response));

		return response;
	}
}
