package com.hatiko.ms.translation.integration.cloud.translation.rest;

import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hatiko.ms.translation.builder.HttpHeadersBuilder;
import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslatedDTO;
import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationRequest;
import com.hatiko.ms.translation.integration.cloud.translation.dto.translation.TranslationResponse;
import com.hatiko.ms.translation.integration.properties.CloudCommunicationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class TranslationCommunicationServiceImpl implements TranslationCommunicationService {

	private final RestTemplate translationRest;
	private final CloudCommunicationProperties cloudTranslationProperties;
	private final HttpHeadersBuilder httpHeadersBuilder;

	@Override
	public TranslationResponse translateText(TranslationRequest request) {

		log.info("POST to translate text | from : {}, to : {}, contents : {} ", request.getSourceLanguageCode(),
				request.getTargetLanguageCode(), request.getContents().stream().collect(Collectors.joining(" ; ")));

		HttpEntity<TranslationRequest> requestEntity = new HttpEntity<>(request, httpHeadersBuilder.getHttpHeaders());

		TranslationResponse response = translationRest.exchange(cloudTranslationProperties.getTranslationUri(),
				HttpMethod.POST, requestEntity, TranslationResponse.class).getBody();

		log.info("Response for translation | body : {}", response.getTranslations().stream()
				.map(TranslatedDTO::getTranslatedText).collect(Collectors.joining(" ; ")));

		return response;
	}
}
