package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.properties.TranslationLanguages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TranslateConfigurationFieldPreparator implements ConfigurationFieldPreparator {

    private final TranslationLanguages translationLanguages;

    @Override
    public String of(String languageArg) {
        if (translationLanguages.getEnglish().getLanguageName().equals(languageArg)
                || translationLanguages.getEnglish().getLanguageShort().equals(languageArg)) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if (translationLanguages.getRussian().getLanguageName().equals(languageArg)
                || translationLanguages.getRussian().getLanguageShort().equals(languageArg)) {
            return translationLanguages.getRussian().getLanguageCode();
        }
        if (translationLanguages.getUkrainian().getLanguageName().equals(languageArg)
                || translationLanguages.getUkrainian().getLanguageShort().equals(languageArg)){
            return translationLanguages.getUkrainian().getLanguageCode();
        }
            return null;
    }

    @Override
    public String ofTo(String from) {
        if(translationLanguages.getUkrainian().getLanguageCode().equals(of(from))) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if(translationLanguages.getRussian().getLanguageCode().equals(of(from))) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if(translationLanguages.getEnglish().getLanguageCode().equals(of(from))) {
            return translationLanguages.getUkrainian().getLanguageCode();
        }
        return null;
    }
}