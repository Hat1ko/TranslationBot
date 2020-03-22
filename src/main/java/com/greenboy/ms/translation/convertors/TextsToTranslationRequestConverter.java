package com.greenboy.ms.translation.convertors;

import java.util.List;

import com.greenboy.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextsToTranslationRequestConverter {

	public static TranslationRequest convert(List<String> text, String fromLanguage, String toLanguage, String mimeType) {

		return TranslationRequest.builder()
				.sourceLanguageCode(fromLanguage)
				.targetLanguageCode(toLanguage)
				.contents(text)
				.mimeType(mimeType)
				.build();
	}
}
