package com.hatiko.ms.translation.integration.properties;

import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hatiko.ms.translation.integration.gcloud.shell.rest.GoogleShellService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(value = "cloud.translation")
@Component
public final class CloudCommunicationProperties {
	
	private final GoogleShellService googleShellService;
	private String accessToken;
	
	private String translationUri;
	private String recognitionUri;
	private String contentType;
	private Timeout timeout;
	
	public String getAccessToken() {
		if(!Optional.ofNullable(accessToken).isPresent()) {
			accessToken = googleShellService.getAccessToken();
		}
		return accessToken;
	}
	
	@Scheduled(cron = "0 0 * ? * *")
	public void setAccessTokenNull() {
		this.accessToken = null;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@ConfigurationProperties(value = "timeout")
	public static class Timeout{
		private Long read;
		private Long connect;
	}
}