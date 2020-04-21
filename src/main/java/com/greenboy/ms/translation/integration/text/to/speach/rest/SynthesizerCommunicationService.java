package com.greenboy.ms.translation.integration.text.to.speach.rest;

import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeResponse;

public interface SynthesizerCommunicationService {
	SynthesizeResponse getSynthesizedString(SynthesizeRequest request);
}
