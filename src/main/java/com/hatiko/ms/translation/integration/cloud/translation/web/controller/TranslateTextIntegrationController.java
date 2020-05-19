package com.hatiko.ms.translation.integration.cloud.translation.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.hatiko.ms.translation.integration.cloud.translation.rest.TranslationCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "gcloud/translate")
public class TranslateTextIntegrationController {

	private final TranslationCommunicationService translationCommunicationService;

	@PostMapping
	public TranslationResponse translateText(@RequestBody TranslationRequest request) {
		
		TranslationResponse response = null;
		
		try {
			response = translationCommunicationService.translateText(request);
		} catch(Exception e) {
			log.error("Error : {}", e.getMessage());
		}
		
		return response;
	}

}
