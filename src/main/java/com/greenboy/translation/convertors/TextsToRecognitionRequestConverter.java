package com.greenboy.translation.convertors;

import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;

@Component
public final class TextsToRecognitionRequestConverter {

	public static RecognitionRequest convert(String text) {
		return RecognitionRequest.builder().content(text).build();
	}
}
