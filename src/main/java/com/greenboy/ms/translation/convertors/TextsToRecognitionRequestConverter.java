package com.greenboy.ms.translation.convertors;

import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextsToRecognitionRequestConverter {

	public static RecognitionRequest convert(String text) {
		return RecognitionRequest.builder().content(text).build();
	}
}
