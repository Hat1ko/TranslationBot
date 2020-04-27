package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.dto.FromToTextDto;
import com.greenboy.bot.telegram.properties.TranslationLanguages;
import com.greenboy.ms.translation.integration.text.to.speech.properties.TextToSpeechProperties;
import com.greenboy.ms.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class FieldPreparator {

    private final TranslationService translationService;
    private final TranslationLanguages translationLanguages;
    private final TextToSpeechProperties textToSpeechProperties;

    public String ofTranslation(String languageArg) {

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

    public String ofToTranslation(String from) {

        if(translationLanguages.getUkrainian().getLanguageCode().equals(ofToTranslation(from))) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if(translationLanguages.getRussian().getLanguageCode().equals(ofToTranslation(from))) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if(translationLanguages.getEnglish().getLanguageCode().equals(ofToTranslation(from))) {
            return translationLanguages.getUkrainian().getLanguageCode();
        }

        return null;
    }

    public FromToTextDto getFromToTextDto(String textToTranslate) {

        String from, to, fromCode, toCode;
        if (languageAvailability(textToTranslate)) {
            from = ArgsExtractor.extractWords(textToTranslate).get(0);
            fromCode = ofTranslation(from);
            textToTranslate = ArgsExtractor.removeFirstWord(textToTranslate);
            if (languageAvailability(textToTranslate)) {
                to = ArgsExtractor.extractWords(textToTranslate).get(0);
                toCode = ofToTranslation(to);
                textToTranslate = ArgsExtractor.removeFirstWord(textToTranslate);
            } else {
                toCode = ofToTranslation(fromCode);
            }
        } else {
            fromCode = translationService.recognizeLanguage(Arrays.asList(textToTranslate)).get(0);
            toCode = ofToTranslation(fromCode);
        }

        return FromToTextDto.builder().fromCode(fromCode).toCode(toCode).modifiedText(textToTranslate).build();
    }

    public Boolean languageAvailability(String textToTranslate) {

        String languageArg = ArgsExtractor.extractWords(textToTranslate).get(0);

        if (translationLanguages.getLanguageNames().stream().filter(lang -> lang.equals(languageArg)).count() > 0) {
            return Boolean.TRUE;
        }
        if (translationLanguages.getLanguageShorts().stream().filter(lang -> lang.equals(languageArg)).count() > 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public String ofSynthesizing(String languageArg) {

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

    public String ofToSynthesizing(String from) {

        if(textToSpeechProperties.getLanguageCode().getEnglish().equals(ofSynthesizing(from))) {
            return textToSpeechProperties.getLanguageCode().getUkrainian();
        }
        if(textToSpeechProperties.getLanguageCode().getRussian().equals(ofSynthesizing(from))) {
            return textToSpeechProperties.getLanguageCode().getEnglish();
        }
        if(textToSpeechProperties.getLanguageCode().getUkrainian().equals(ofSynthesizing(from))) {
            return textToSpeechProperties.getLanguageCode().getEnglish();
        }

        return null;
    }
}
