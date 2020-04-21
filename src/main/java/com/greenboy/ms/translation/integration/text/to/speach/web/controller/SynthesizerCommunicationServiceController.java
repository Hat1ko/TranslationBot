package com.greenboy.ms.translation.integration.text.to.speach.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeResponse;
import com.greenboy.ms.translation.integration.text.to.speach.rest.SynthesizerCommunicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/gcloud/syntesize")
@RestController
public class SynthesizerCommunicationServiceController {
	
	private final SynthesizerCommunicationService synthesizerCommunicationService;
	
	@PostMapping
	public ResponseEntity<SynthesizeResponse> syntesizeText(@RequestBody SynthesizeRequest request) {
		
		SynthesizeResponse response = synthesizerCommunicationService.getSynthesizedString(request);
		
		return ResponseEntity.ok(response);
	}
}
