package com.greenboy.ms.translation.integration.cloud.translation.rest;

import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;

public interface RecognitionCommunicationService {
	RecognitionResponse recognizeText(RecognitionRequest request);
}