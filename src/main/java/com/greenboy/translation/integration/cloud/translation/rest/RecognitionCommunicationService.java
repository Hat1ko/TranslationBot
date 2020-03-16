package com.greenboy.translation.integration.cloud.translation.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;

public interface RecognitionCommunicationService {
	RecognitionResponse recognizeText(RecognitionRequest request) throws JsonProcessingException;
}
