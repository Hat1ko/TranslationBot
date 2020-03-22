package com.greenboy.ms.translation.integration.text.to.speach.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeResponse;
import com.greenboy.ms.translation.integration.text.to.speach.rest.SyntesizerCommunicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/gcloud/syntesize")
@RestController
public class SyntesizerCommunicationServiceController {
	
	private final SyntesizerCommunicationService syntesizerCommunicationService;
	
	@PostMapping
	public ResponseEntity<SyntesizeResponse> syntesizeText(@RequestBody SyntesizeRequest request) {
		
		SyntesizeResponse response = syntesizerCommunicationService.getSyntesizedString(request);
		
		return ResponseEntity.ok(response);
	}
}
