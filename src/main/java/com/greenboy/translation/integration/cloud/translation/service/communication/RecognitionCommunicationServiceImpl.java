package com.greenboy.translation.integration.cloud.translation.service.communication;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.translation.properties.CloudTranslationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecognitionCommunicationServiceImpl implements RecognitionCommunicationService {

	private final RestTemplate recognitionRest;
	private final CloudTranslationProperties cloudTranslationProperties;
	private final ObjectMapper objectMapper;

	@Override
	public RecognitionResponse recognizeText(RecognitionRequest recognitionRequest) throws JsonProcessingException {
		
		log.info("POST for recognition | content : {}", recognitionRequest.getContent());
		
		RecognitionResponse response = recognitionRest.postForEntity(cloudTranslationProperties.getRecognitionUri(),
				recognitionRequest, RecognitionResponse.class).getBody();
		
		log.info("Response for recognition | body : {}", objectMapper.writeValueAsString(response));
		
		return response;
	}
}
