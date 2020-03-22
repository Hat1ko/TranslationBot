package com.greenboy.ms.translation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.greenboy.ms.translation.convertors.RecognitionResponseToLanguageConverter;
import com.greenboy.ms.translation.convertors.TextsToRecognitionRequestConverter;
import com.greenboy.ms.translation.convertors.TextsToTranslationRequestConverter;
import com.greenboy.ms.translation.convertors.TranslationResponseToTextsConverter;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.ms.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.greenboy.ms.translation.integration.cloud.translation.properties.TranslationRequestProperties;
import com.greenboy.ms.translation.integration.cloud.translation.rest.RecognitionCommunicationService;
import com.greenboy.ms.translation.integration.cloud.translation.rest.TranslationCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class TranslationServiceImpl implements TranslationService {

	private final TranslationCommunicationService translationCommunicationService;
	private final RecognitionCommunicationService recognitionCommunicationService;
	private final TranslationRequestProperties translationRequestProperties;
	
	@Override
	public List<String> translateText(List<String> texts, String fromLanguage, String toLanguage) {

		String mimeType = translationRequestProperties.getMimeType();
		
		TranslationRequest request = TextsToTranslationRequestConverter.convert(texts, fromLanguage, toLanguage, mimeType);

		log.info("Request to translate from : {}, to : {}", fromLanguage, toLanguage);

		TranslationResponse response = translationCommunicationService.translateText(request);

		List<String> translatedTexts = TranslationResponseToTextsConverter.convert(response);

		log.info("Response : {}", translatedTexts.stream().collect(Collectors.joining(" | ")));

		return translatedTexts;
	}

	@Override
	public List<String> recognizeLanguage(List<String> texts) {

		List<RecognitionRequest> requests = texts.stream().map(TextsToRecognitionRequestConverter::convert)
				.collect(Collectors.toList());

		log.info("Requests to recognize texts | num of requests : {}", requests.size());
		
		List<RecognitionResponse> responses = requests.stream().map(recognitionCommunicationService::recognizeText)
				.collect(Collectors.toList());

		log.info("Responses from recognize texts | num of responses : {}", responses.size());
		
		List<String> recognizedLanguages = responses.stream().map(RecognitionResponseToLanguageConverter::convert)
				.collect(Collectors.toList());
		
		return recognizedLanguages;
	}
}
