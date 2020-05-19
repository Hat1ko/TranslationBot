package com.hatiko.ms.translation.integration.text.to.speech.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeRequest;
import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeResponse;
import com.hatiko.ms.translation.integration.text.to.speech.rest.SynthesizerCommunicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "gcloud/synthesize")
@RestController
public class SynthesizerCommunicationServiceController {
	
	private final SynthesizerCommunicationService synthesizerCommunicationService;
	
	@PostMapping
	public ResponseEntity<SynthesizeResponse> synthesizeText(@RequestBody SynthesizeRequest request) {
		
		SynthesizeResponse response = synthesizerCommunicationService.getSynthesizedString(request);
		
		return ResponseEntity.ok(response);
	}
}
