package com.greenboy.ms.translation.integration.text.to.speech.rest;

import com.greenboy.ms.translation.integration.text.to.speech.dto.SynthesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speech.dto.SynthesizeResponse;

public interface SynthesizerCommunicationService {
	SynthesizeResponse getSynthesizedString(SynthesizeRequest request);
}
