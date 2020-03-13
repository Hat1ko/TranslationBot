package com.greenboy.translation.integration.cloud.translation.properties;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.service.GoogleShellService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Component
public class CloudTranslationProperties {
	
	private final GoogleShellService googleShellService;
	private String accessToken;
	
//TODO:	
//	@Scheduled(cron = )
	public String getAccessToken() {
		
		if(!Optional.ofNullable(accessToken).isPresent()) {
			accessToken = googleShellService.getAccessToken();
		}
		return accessToken;
	}
}
