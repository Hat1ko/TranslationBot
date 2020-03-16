package com.greenboy.translation.integration.cloud.translation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;

public interface TranslationCommunicationService {
	TranslationResponse translateText(TranslationRequest request) throws JsonProcessingException;
}
