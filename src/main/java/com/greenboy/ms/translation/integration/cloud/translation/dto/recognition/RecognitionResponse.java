package com.greenboy.ms.translation.integration.cloud.translation.dto.recognition;

import java.util.List;

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
public class RecognitionResponse {
	
	List<RecognizedLanguageDTO> languages;
}
