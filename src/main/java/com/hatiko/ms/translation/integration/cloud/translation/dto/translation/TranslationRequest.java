package com.hatiko.ms.translation.integration.cloud.translation.dto.translation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequest {
	
	private String sourceLanguageCode;
	private String targetLanguageCode;
	private List<String> contents;
	private String mimeType;
}
