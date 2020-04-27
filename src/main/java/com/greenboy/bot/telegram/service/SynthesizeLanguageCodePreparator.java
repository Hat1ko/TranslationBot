package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.properties.TranslationLanguages;
import com.greenboy.ms.translation.integration.text.to.speech.properties.TextToSpeechProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SynthesizeLanguageCodePreparator implements LanguageCodePreparator {

    private final TextToSpeechProperties textToSpeechProperties;
    private final TranslationLanguages translationLanguages;

    @Override
    public String of(String languageArg) {
        if (translationLanguages.getEnglish().getLanguageName().equals(languageArg)
                || translationLanguages.getEnglish().getLanguageShort().equals(languageArg)) {
            return textToSpeechProperties.getLanguageCode().getEnglish();
        }
        if (translationLanguages.getRussian().getLanguageName().equals(languageArg)
                || translationLanguages.getRussian().getLanguageShort().equals(languageArg)) {
            return textToSpeechProperties.getLanguageCode().getRussian();
        }
        if (translationLanguages.getUkrainian().getLanguageName().equals(languageArg)
                || translationLanguages.getUkrainian().getLanguageShort().equals(languageArg)) {
            return textToSpeechProperties.getLanguageCode().getUkrainian();
        }
        return null;
    }

    @Override
    public String ofTo(String from) {
        if(textToSpeechProperties.getLanguageCode().getEnglish().equals(of(from))) {
            return textToSpeechProperties.getLanguageCode().getUkrainian();
        }
        if(textToSpeechProperties.getLanguageCode().getRussian().equals(of(from))) {
            return textToSpeechProperties.getLanguageCode().getEnglish();
        }
        if(textToSpeechProperties.getLanguageCode().getUkrainian().equals(of(from))) {
            return textToSpeechProperties.getLanguageCode().getEnglish();
        }
        return null;
    }
}
