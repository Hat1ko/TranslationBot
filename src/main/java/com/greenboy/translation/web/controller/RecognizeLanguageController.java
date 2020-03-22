package com.greenboy.translation.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.service.TranslationService;
import com.greenboy.translation.web.dto.request.RecognizeLanguageRequest;
import com.greenboy.translation.web.dto.response.RecognizeLanguageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/recognize")
@RestController
public class RecognizeLanguageController {

	private final TranslationService translationService;

	@PostMapping
	public ResponseEntity<RecognizeLanguageResponse> recognizeLanguage(@RequestBody RecognizeLanguageRequest request) {

		List<String> texts = request.getContents();

		List<String> recognizedLanguages = translationService.recognizeLanguage(texts);

		RecognizeLanguageResponse response = RecognizeLanguageResponse.builder()
				.recognizedLanguages(recognizedLanguages).build();

		return ResponseEntity.ok(response);

	}
}
