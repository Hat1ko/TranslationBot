package com.greenboy.translation.properties;

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
	
	private String languageCodeUkrainian;
	private String languageCodeRussian;
	private String languageCodeEnglish;
	private String mimeType;
}
