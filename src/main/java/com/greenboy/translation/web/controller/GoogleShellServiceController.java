package com.greenboy.translation.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.integration.service.GoogleShellService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class GoogleShellServiceController {

	private final GoogleShellService googleShellService;
	
	@GetMapping
	public ResponseEntity<String> getAccessToken(){
		return ResponseEntity.ok(googleShellService.getAccessToken());
	}
}
