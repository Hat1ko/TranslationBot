package com.greenboy.translation.integration.text.to.speach.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(value = "text.to.speach")
@Component
public class TextToSpeachProperties {

	private String uri;
	private String audioEncoding;
	private LanguageCode languageCode;
	private SsmlGender ssmlGender;
	private SyntesizerName syntesizerName;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LanguageCode {

		private String english;
		private String russian;
		private String ukrainian;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SsmlGender {

		private String male;
		private String female;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SyntesizerName {
		
		private String english;
		private String russian;
		private String ukrainian;
	}
}
