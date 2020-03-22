package com.greenboy.ms.translation.convertors;

import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RecognitionResponseToLanguageConverter {
	
	public static String convert(RecognitionResponse response) {
		return response.getLanguages().get(0).getLanguageCode();
	}
}
