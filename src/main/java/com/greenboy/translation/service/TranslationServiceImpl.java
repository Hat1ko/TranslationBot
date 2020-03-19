package com.greenboy.translation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.greenboy.translation.convertors.TextsToTranslationRequestConverter;
import com.greenboy.translation.convertors.TranslationResponseToTextsConverter;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.greenboy.translation.integration.cloud.translation.rest.TranslationCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TranslationServiceImpl implements TranslationService{

	private final TranslationCommunicationService translationCommunicationService;
	private final TextsToTranslationRequestConverter textsToTranslationRequestConverter;
	
	@Override
	public List<String> translateText(List<String> texts, String fromLanguage, String toLanguage) {
		
		TranslationRequest request = textsToTranslationRequestConverter.convert(texts, fromLanguage, toLanguage);
		
		log.info("Request to translate from : {}, to : {}", fromLanguage, toLanguage);
		
		TranslationResponse response = translationCommunicationService.translateText(request);
		
		List<String> translatedTexts = TranslationResponseToTextsConverter.convert(response); 
		
		log.info("Response : {}", translatedTexts.stream().collect(Collectors.joining(" | ")));
		
		return translatedTexts;
	}

	@Override
	public List<String> recognizeLanguage(List<String> texts) {
		
		return null;
	}
}
