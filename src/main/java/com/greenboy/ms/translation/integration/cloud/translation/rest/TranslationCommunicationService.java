package com.greenboy.ms.translation.integration.cloud.translation.rest;

import com.greenboy.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.translation.TranslationResponse;

public interface TranslationCommunicationService {
	TranslationResponse translateText(TranslationRequest request);
}
