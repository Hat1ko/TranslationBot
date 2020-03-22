package com.greenboy.ms.translation.integration.cloud.translation.dto.recognition;

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
public class RecognizedLanguageDTO {
	
	String languageCode;
	Double confidence;
}
