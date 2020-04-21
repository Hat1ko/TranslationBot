package com.greenboy.ms.translation.integration.text.to.speach.dto;

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
public class VoiceDTO {
	
	private String languageCode;
	private String name;
	private String ssmlGender;
}