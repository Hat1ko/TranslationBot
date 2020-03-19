package com.greenboy.translation.convertors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.properties.TranslationRequestProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TextsToTranslationRequestConverter {

	private final TranslationRequestProperties translationRequestProperties;

	public TranslationRequest convert(List<String> text, String fromLanguage, String toLanguage) {

		return TranslationRequest.builder()
				.sourceLanguageCode(fromLanguage)
				.targetLanguageCode(toLanguage)
				.contents(text)
				.mimeType(translationRequestProperties.getMimeType())
				.build();
	}
}
