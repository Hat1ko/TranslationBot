package com.greenboy.translation.integration.cloud.translation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(value = "translation")
@Component
public class TranslationRequestProperties {
	
	private LanguageCode languageCode;
	private String mimeType;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ConfigurationProperties(value = "language-code")
	public static class LanguageCode{
		
		private String english;
		private String russian;
		private String ukrainian;
	}
}
