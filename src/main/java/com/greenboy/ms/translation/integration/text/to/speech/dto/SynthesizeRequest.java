package com.greenboy.ms.translation.integration.text.to.speech.dto;

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
public class SynthesizeRequest {
	
	private InputDTO input;
	private VoiceDTO voice;
	private AudioConfigDTO audioConfig;
}
