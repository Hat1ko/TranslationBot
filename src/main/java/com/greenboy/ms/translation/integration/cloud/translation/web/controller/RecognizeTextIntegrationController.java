package com.greenboy.ms.translation.integration.cloud.translation.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.ms.translation.integration.cloud.translation.rest.RecognitionCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/gcloud/recognize")
public class RecognizeTextIntegrationController {

	private final RecognitionCommunicationService recognitionCommuniationService;
	
	@PostMapping
	public RecognitionResponse recognizeLanguage(@RequestBody RecognitionRequest request) {
		
		RecognitionResponse response = null;
		
		try {
			response = recognitionCommuniationService.recognizeText(request);
		}catch(Exception e) {
			log.error("Error : {}", e.getMessage());
		}
		
		return response;
	}
}
