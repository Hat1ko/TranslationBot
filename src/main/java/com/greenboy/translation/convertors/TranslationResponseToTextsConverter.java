package com.greenboy.translation.convertors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslatedDTO;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;

@Component
public final class TranslationResponseToTextsConverter {

	public static List<String> convert(TranslationResponse response) {
		return response.getTranslations().stream()
				.map(TranslatedDTO::getTranslatedText)
				.collect(Collectors.toList());
	}
}