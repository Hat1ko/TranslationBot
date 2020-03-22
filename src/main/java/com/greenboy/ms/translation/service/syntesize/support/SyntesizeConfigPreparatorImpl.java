package com.greenboy.ms.translation.service.syntesize.support;

import org.springframework.stereotype.Component;

import com.greenboy.ms.translation.dto.SyntesizeConfig;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeachProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SyntesizeConfigPreparatorImpl implements SyntesizeConfigPreparator {

	private final TextToSpeachProperties textToSpeachProperties;

	@Override
	public SyntesizeConfig prepareConfig(String language, String gender) {

		String languageCode = "en";
		String syntesizerName = textToSpeachProperties.getSyntesizerName().getEnglish().getFemale();

		switch (language) {
		case "en":
			languageCode = textToSpeachProperties.getLanguageCode().getEnglish();
			syntesizerName = textToSpeachProperties.getSsmlGender().getMale().equals(gender)
					? textToSpeachProperties.getSyntesizerName().getEnglish().getMale()
					: textToSpeachProperties.getSyntesizerName().getEnglish().getFemale();
			break;
		case "ru":
			languageCode = textToSpeachProperties.getLanguageCode().getRussian();
			syntesizerName = textToSpeachProperties.getSsmlGender().getMale().equals(gender)
					? textToSpeachProperties.getSyntesizerName().getRussian().getMale()
					: textToSpeachProperties.getSyntesizerName().getRussian().getFemale();
			break;
		case "uk":
			languageCode = textToSpeachProperties.getLanguageCode().getUkrainian();
			syntesizerName = textToSpeachProperties.getSyntesizerName().getUkrainian().getFemale();// no male
			break;
		}

		String ssmlGender = textToSpeachProperties.getSsmlGender().getMale().equals(gender)
				? textToSpeachProperties.getSsmlGender().getMale()
				: textToSpeachProperties.getSsmlGender().getFemale();

		return SyntesizeConfig.builder().languageCode(languageCode).ssmlGender(ssmlGender)
				.syntesizerName(syntesizerName).build();
	}
}
