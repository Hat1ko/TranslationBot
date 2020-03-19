package com.greenboy.translation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.greenboy.translation.convertors.RecognitionResponseToLanguageConverter;
import com.greenboy.translation.convertors.TextsToRecognitionRequestConverter;
import com.greenboy.translation.convertors.TextsToTranslationRequestConverter;
import com.greenboy.translation.convertors.TranslationResponseToTextsConverter;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionRequest;
import com.greenboy.translation.integration.cloud.translation.dto.recognition.RecognitionResponse;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.greenboy.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.greenboy.translation.integration.cloud.translation.rest.RecognitionCommunicationService;
import com.greenboy.translation.integration.cloud.translation.rest.TranslationCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class TranslationServiceImpl implements TranslationService {

	private final TranslationCommunicationService translationCommunicationService;
	private final RecognitionCommunicationService recognitionCommunicationService;
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
