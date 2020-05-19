package com.hatiko.ms.translation.integration.text.to.speech.rest;

import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeRequest;
import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeResponse;

public interface SynthesizerCommunicationService {
	SynthesizeResponse getSynthesizedString(SynthesizeRequest request);
}
