package com.greenboy.ms.translation.integration.text.to.speach.rest;

import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeResponse;

public interface SyntesizerCommunicationService {
	SyntesizeResponse getSyntesizedString(SyntesizeRequest request);
}
