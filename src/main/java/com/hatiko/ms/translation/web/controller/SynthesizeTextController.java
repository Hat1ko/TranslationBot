package com.hatiko.ms.translation.web.controller;

import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatiko.ms.translation.service.SynthesizeService;
import com.hatiko.ms.translation.web.dto.request.SynthesizeTextRequest;
import com.hatiko.ms.translation.web.dto.response.SynthesizeTextResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/synthesize")
@RestController
public class SynthesizeTextController {

	private final SynthesizeService synthesizeService;

	@PostMapping
	public ResponseEntity<SynthesizeTextResponse> synthesizeText(@RequestBody SynthesizeTextRequest request) {

		log.info("API call to synthesize texts | language : {}, gender : {}, content : {}", request.getLanguage(),
				request.getGender(), request.getContent());

		Path path = synthesizeService.synthesizeText(request.getContent(), request.getLanguage(), request.getGender());

		log.info("API response to synthesize texts");

		SynthesizeTextResponse response = SynthesizeTextResponse.builder().paths(path).build();

		return ResponseEntity.ok(response);
	}
}