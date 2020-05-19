package com.hatiko.ms.translation.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatiko.ms.translation.integration.cloud.translation.properties.TranslationRequestProperties;
import com.hatiko.ms.translation.service.TranslationService;
import com.hatiko.ms.translation.web.dto.request.TranslateTextRequest;
import com.hatiko.ms.translation.web.dto.response.TranslateTextResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/translate")
@RestController
public class TranslateTextController {

	private final TranslationService translationService;
	private final TranslationRequestProperties translationRequestProperties;

	@PostMapping
	public ResponseEntity<TranslateTextResponse> translateText(@RequestBody TranslateTextRequest request) {

		List<String> texts = request.getContents();

		log.info("API call to translate texts | num of texts: {}", texts.size());

		List<String> translatedTexts = translationService.translateText(texts,
				translationRequestProperties.getLanguageCode().getEnglish(),
				translationRequestProperties.getLanguageCode().getUkrainian());

		log.info("API response to translate texts | num of translations : {}", translatedTexts.size());

		TranslateTextResponse response = TranslateTextResponse.builder().translatedContents(translatedTexts).build();

		return ResponseEntity.ok(response);
	}
}
