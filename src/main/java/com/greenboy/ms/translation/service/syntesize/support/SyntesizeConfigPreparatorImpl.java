package com.greenboy.ms.translation.service.syntesize.support;

import org.springframework.stereotype.Component;

import com.greenboy.ms.translation.dto.SyntesizeEntity;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeachProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SyntesizeConfigPreparatorImpl implements SyntesizeConfigPreparator {

	private final TextToSpeachProperties textToSpeachProperties;

	@Override
	public SyntesizeEntity prepareConfig(String language, String gender) {

		String languageCode = "en";
		String syntesizerName = textToSpeachProperties.getSyntesizerName().getEnglish().getFemale();

		switch (language) {
		case "en":
			languageCode = textToSpeachProperties.getLanguageCode().getEnglish();
			syntesizerName = textToSpeachProperties.getSyntesizerName().getEnglish().getMale().equals(gender)
					? textToSpeachProperties.getSyntesizerName().getEnglish().getMale()
					: textToSpeachProperties.getSyntesizerName().getEnglish().getFemale();
			break;
		case "ru":
			languageCode = textToSpeachProperties.getLanguageCode().getRussian();
			syntesizerName = textToSpeachProperties.getSyntesizerName().getRussian().getMale().equals(gender)
					? textToSpeachProperties.getSyntesizerName().getRussian().getMale()
					: textToSpeachProperties.getSyntesizerName().getRussian().getFemale();
			break;
		case "uk":
			languageCode = textToSpeachProperties.getLanguageCode().getUkrainian();
			syntesizerName = textToSpeachProperties.getSyntesizerName().getEnglish().getFemale();// no male
			break;
		}

		String ssmlGender = textToSpeachProperties.getSsmlGender().getFemale().equals(gender)
				? textToSpeachProperties.getSsmlGender().getFemale()
				: textToSpeachProperties.getSsmlGender().getMale();

		return SyntesizeEntity.builder().languageCode(languageCode).ssmlGender(ssmlGender)
				.syntesizerName(syntesizerName).build();
	}
}
