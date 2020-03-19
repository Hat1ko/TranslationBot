package com.greenboy.translation.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.integration.cloud.translation.properties.TranslationRequestProperties;
import com.greenboy.translation.service.TranslationService;
import com.greenboy.translation.web.dto.request.TranslateTextRequest;
import com.greenboy.translation.web.dto.response.TranslateTextResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/translation")
@RestController
public class TranslateTextController {

	private final TranslationService translationService;
	private final TranslationRequestProperties translationRequestProperties;

	@PostMapping
	public ResponseEntity<TranslateTextResponse> translateText(@RequestBody TranslateTextRequest request) {

		List<String> translatedTexts = translationService.translateText(request.getContents(),
				translationRequestProperties.getLanguageCode().getEnglish(),
				translationRequestProperties.getLanguageCode().getUkrainian());

		TranslateTextResponse response = TranslateTextResponse.builder().translatedContents(translatedTexts).build();

		return ResponseEntity.ok(response);
	}
}
