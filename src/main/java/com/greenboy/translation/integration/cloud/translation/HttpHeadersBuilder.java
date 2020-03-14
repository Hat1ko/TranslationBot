package com.greenboy.translation.integration.cloud.translation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.greenboy.translation.properties.CloudTranslationProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HttpHeadersBuilder {
	
	private final CloudTranslationProperties cloudTranslationProperties;
	
	public HttpHeaders getHttpHeaders() {
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 headers.set("Authorization", cloudTranslationProperties.getAccessToken());
		 
		 return headers;
	}
}
