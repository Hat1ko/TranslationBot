package com.greenboy.translation.integration.text.to.speach.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.greenboy.translation.builder.HttpHeadersBuilder;
import com.greenboy.translation.integration.text.to.speach.dto.SyntesizeRequest;
import com.greenboy.translation.integration.text.to.speach.dto.SyntesizeResponse;
import com.greenboy.translation.integration.text.to.speach.properties.TextToSpeachProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SyntesizerCommunicationServiceImpl implements SyntesizerCommunicationService {

	private final RestTemplate syntesizerRest;
	private final HttpHeadersBuilder httpHeadersBuilder;
	private final TextToSpeachProperties textToSpeachProperties;

	@Override
	public SyntesizeResponse getSyntesierString(SyntesizeRequest request) {

		log.info("POST for syntesation | languageCode : {}, text : {}", request.getVoice().getLanguageCode(), request.getInput().getText());
		
		HttpEntity<SyntesizeRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		SyntesizeResponse response = syntesizerRest.exchange(textToSpeachProperties.getUri(), HttpMethod.POST,
				requestEntity, SyntesizeResponse.class).getBody();

		log.info("Response from syntesation | audioContent : {}", response.getAudioContent());
		
		return response;
	}

}
