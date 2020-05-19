package com.hatiko.ms.translation.integration.text.to.speech.properties;

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
@ConfigurationProperties(value = "text.to.speech")
@Component
public class TextToSpeechProperties {

	private String uri;
	private String audioEncoding;
	private LanguageCode languageCode;
	private SsmlGender ssmlGender;
	private SyntesizerName syntesizerName;
	private Timeout timeout;

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

		private English english;
		private Russian russian;
		private Ukrainian ukrainian;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class English {
			String male;
			String female;
		}
		
		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Russian {
			String male;
			String female;
		}
		
		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Ukrainian {
			String male;
			String female;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Timeout {

		private Long read;
		private Long connect;
	}
}
