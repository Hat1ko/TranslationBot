package com.greenboy.translation.web.controller;

import java.nio.file.Path;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.service.SyntesizeService;
import com.greenboy.translation.web.dto.request.SyntesizeTextRequest;
import com.greenboy.translation.web.dto.response.SyntesizeTextResponse;

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
		
		List<String> textsToSyntesize = request.getContents();
		
		List<Path> paths = syntesizeService.syntesizeText(textsToSyntesize);
		
		SyntesizeTextResponse response = SyntesizeTextResponse.builder().paths(paths).build();
		
		return ResponseEntity.ok(response);
	}
}