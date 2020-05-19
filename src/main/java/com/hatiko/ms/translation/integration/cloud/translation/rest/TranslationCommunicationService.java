package com.hatiko.ms.translation.integration.cloud.translation.rest;

import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationResponse;

public interface TranslationCommunicationService {
	TranslationResponse translateText(TranslationRequest request);
}
