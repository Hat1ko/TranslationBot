package com.hatiko.ms.translation.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatiko.ms.translation.service.TranslationService;
import com.hatiko.ms.translation.web.dto.request.RecognizeLanguageRequest;
import com.hatiko.ms.translation.web.dto.response.RecognizeLanguageResponse;

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

		log.info("API call to recognize language | num of texts : {}", texts.size());
		
		List<String> recognizedLanguages = translationService.recognizeLanguage(texts);

		log.info("API response to recognize language");
		
		RecognizeLanguageResponse response = RecognizeLanguageResponse.builder()
				.recognizedLanguages(recognizedLanguages).build();

		return ResponseEntity.ok(response);
	}
}
