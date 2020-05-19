package com.hatiko.ms.translation.integration.text.to.speech.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hatiko.ms.translation.builder.HttpHeadersBuilder;
import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeRequest;
import com.hatiko.ms.translation.integration.text.to.speech.dto.SynthesizeResponse;
import com.hatiko.ms.translation.integration.text.to.speech.properties.TextToSpeechProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SynthesizerCommunicationServiceImpl implements SynthesizerCommunicationService {

	private final RestTemplate synthesizerRest;
	private final HttpHeadersBuilder httpHeadersBuilder;
	private final TextToSpeechProperties textToSpeechProperties;

	@Override
	public SynthesizeResponse getSynthesizedString(SynthesizeRequest request) {

		log.info("POST to synthesize | languageCode : {}, text : {}", request.getVoice().getLanguageCode(),
				request.getInput().getText());

		HttpEntity<SynthesizeRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		SynthesizeResponse response = synthesizerRest
				.exchange(textToSpeechProperties.getUri(), HttpMethod.POST, requestEntity, SynthesizeResponse.class)
				.getBody();

		log.info("Response from POST to synthesize | audioContent : {}", response.getAudioContent());

		return response;
	}

}
