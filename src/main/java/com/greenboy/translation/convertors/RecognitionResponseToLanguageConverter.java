package com.greenboy.translation.convertors;

import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;

@Component
public final class RecognitionResponseToLanguageConverter {
	
	public static String convert(RecognitionResponse response) {
		return response.getLanguages().get(0).getLanguageCode();
	}
}
