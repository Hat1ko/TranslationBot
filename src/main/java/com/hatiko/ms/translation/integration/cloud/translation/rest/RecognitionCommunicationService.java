package com.hatiko.ms.translation.integration.cloud.translation.rest;

import com.hatiko.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.hatiko.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;

public interface RecognitionCommunicationService {
	RecognitionResponse recognizeText(RecognitionRequest request);
}
