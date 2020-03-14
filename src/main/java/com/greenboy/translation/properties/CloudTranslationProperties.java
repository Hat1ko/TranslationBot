package com.greenboy.translation.properties;

import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.greenboy.translation.integration.cloud.translation.service.GoogleShellService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(value = "cloud.translation")
@Component
public class CloudTranslationProperties {
	
	private final GoogleShellService googleShellService;
	private String accessToken;
	
	private String TranslationUri;
	private String RecognitionUri;
	private String contentType;
	private Timeout timeout;
	
//TODO:	
//	@Scheduled(cron = )
	public String getAccessToken() {
		
		if(!Optional.ofNullable(accessToken).isPresent()) {
			accessToken = googleShellService.getAccessToken();
		}
		return accessToken;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	public class Timeout{
		private Long read;
		private Long connect;
	}
}