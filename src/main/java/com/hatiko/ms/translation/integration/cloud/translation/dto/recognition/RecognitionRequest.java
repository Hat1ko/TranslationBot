package com.hatiko.ms.translation.integration.cloud.translation.dto.recognition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecognitionRequest {
	private String content; 
}
