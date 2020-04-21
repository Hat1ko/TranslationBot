package com.greenboy.ms.translation.service.syntesize.support;

import org.springframework.stereotype.Component;

import com.greenboy.ms.translation.dto.SynthesizeConfig;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeechProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SynthesizeConfigPreparatorImpl implements SynthesizeConfigPreparator {

	private final TextToSpeechProperties textToSpeechProperties;

	@Override
	public SynthesizeConfig prepareConfig(String language, String gender) {

		String languageCode = "en";
		String synthesizerName = textToSpeechProperties.getSyntesizerName().getEnglish().getFemale();

		switch (language) {
		case "en":
			languageCode = textToSpeechProperties.getLanguageCode().getEnglish();
			synthesizerName = textToSpeechProperties.getSsmlGender().getMale().equals(gender)
					? textToSpeechProperties.getSyntesizerName().getEnglish().getMale()
					: textToSpeechProperties.getSyntesizerName().getEnglish().getFemale();
			break;
		case "ru":
			languageCode = textToSpeechProperties.getLanguageCode().getRussian();
			synthesizerName = textToSpeechProperties.getSsmlGender().getMale().equals(gender)
					? textToSpeechProperties.getSyntesizerName().getRussian().getMale()
					: textToSpeechProperties.getSyntesizerName().getRussian().getFemale();
			break;
		case "uk":
			languageCode = textToSpeechProperties.getLanguageCode().getUkrainian();
			synthesizerName = textToSpeechProperties.getSyntesizerName().getUkrainian().getFemale();// no male
			break;
		}

		String ssmlGender = textToSpeechProperties.getSsmlGender().getMale().equals(gender)
				? textToSpeechProperties.getSsmlGender().getMale()
				: textToSpeechProperties.getSsmlGender().getFemale();

		return SynthesizeConfig.builder().languageCode(languageCode).ssmlGender(ssmlGender)
				.synthesizerName(synthesizerName).build();
	}
}
