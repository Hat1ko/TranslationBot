package com.greenboy.ms.translation.web.controller;

import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.ms.translation.service.SyntesizeService;
import com.greenboy.ms.translation.web.dto.request.SyntesizeTextRequest;
import com.greenboy.ms.translation.web.dto.response.SyntesizeTextResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/syntesize")
@RestController
public class SyntesizeTextController {

	private final SyntesizeService syntesizeService;

	@PostMapping
	public ResponseEntity<SyntesizeTextResponse> syntesizeText(@RequestBody SyntesizeTextRequest request) {

		log.info("API call to syntesize texts | language : {}, gender : {}, content : {}", request.getLanguage(),
				request.getGender(), request.getContent());

		Path path = syntesizeService.syntesizeText(request.getContent(), request.getLanguage(), request.getGender());

		log.info("API response to syntesize texts");

		SyntesizeTextResponse response = SyntesizeTextResponse.builder().paths(path).build();

		return ResponseEntity.ok(response);
	}
}