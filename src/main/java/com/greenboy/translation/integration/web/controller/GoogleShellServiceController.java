package com.greenboy.translation.integration.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.integration.service.GoogleShellService;
import com.greenboy.translation.integration.web.model.AccessTokenDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class GoogleShellServiceController {

	private final GoogleShellService googleShellService;

	@GetMapping
	public ResponseEntity<AccessTokenDTO> getAccessToken() {
		return ResponseEntity.ok(AccessTokenDTO.builder().accessToken(googleShellService.getAccessToken()).build());
	}
}
