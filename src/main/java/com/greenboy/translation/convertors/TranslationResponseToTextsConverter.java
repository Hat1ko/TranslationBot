package com.greenboy.translation.convertors;

import java.util.List;
import java.util.stream.Collectors;

import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslatedDTO;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TranslationResponseToTextsConverter {

	public static List<String> convert(TranslationResponse response) {
		return response.getTranslations().stream()
				.map(TranslatedDTO::getTranslatedText)
				.collect(Collectors.toList());
	}
}