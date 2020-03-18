package com.greenboy.translation.integration.cloud.translation.rest;

import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;

public interface TranslationCommunicationService {
	TranslationResponse translateText(TranslationRequest request);
}
