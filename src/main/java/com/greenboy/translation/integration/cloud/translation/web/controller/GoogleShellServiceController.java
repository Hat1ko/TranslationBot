package com.greenboy.translation.integration.cloud.translation.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenboy.translation.integration.cloud.translation.service.GoogleShellService;
import com.greenboy.translation.integration.cloud.translation.web.dto.AccessTokenDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/gcloud/access_token")
public class GoogleShellServiceController {

	private final GoogleShellService googleShellService;

	@GetMapping
	public ResponseEntity<AccessTokenDTO> getAccessToken() {
		return ResponseEntity.ok(AccessTokenDTO.builder().accessToken(googleShellService.getAccessToken()).build());
	}
}
