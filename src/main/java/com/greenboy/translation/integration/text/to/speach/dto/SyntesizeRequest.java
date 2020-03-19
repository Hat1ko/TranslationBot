package com.greenboy.translation.integration.text.to.speach.dto;

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
public class SyntesizeRequest {
	
	private InputDTO input;
	private VoiceDTO voice;
	private AudioConfigDTO audioConfig;
}