package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.dto.FromToTextDto;
import com.greenboy.bot.telegram.dto.LanguageAndGenderDto;
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
                || translationLanguages.getUkrainian().getLanguageShort().equals(languageArg)) {
            return translationLanguages.getUkrainian().getLanguageCode();
        }

        return null;
    }

    public String ofToTranslation(String from) {

        if (translationLanguages.getUkrainian().getLanguageCode().equals(from)
                || translationLanguages.getUkrainian().getLanguageShort().equals(from)
                || translationLanguages.getUkrainian().getLanguageName().equals(from)) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if (translationLanguages.getRussian().getLanguageCode().equals(from)
                || translationLanguages.getRussian().getLanguageShort().equals(from)
                || translationLanguages.getRussian().getLanguageName().equals(from)) {
            return translationLanguages.getEnglish().getLanguageCode();
        }
        if (translationLanguages.getEnglish().getLanguageCode().equals(from)
                || translationLanguages.getEnglish().getLanguageShort().equals(from)
                || translationLanguages.getEnglish().getLanguageName().equals(from)) {
            return translationLanguages.getUkrainian().getLanguageCode();
        }

        return null;
    }

    public FromToTextDto getFromToTextDto(String textToTranslate) {

        String from, to, fromCode, toCode = null;
        if (languageAvailability(textToTranslate)) {
            from = ArgsExtractor.extractWords(textToTranslate).get(0);
            fromCode = ofTranslation(from);
            textToTranslate = ArgsExtractor.removeFirstWord(textToTranslate);
            if (languageAvailability(textToTranslate)) {
                to = ArgsExtractor.extractWords(textToTranslate).get(0);
                toCode = ofTranslation(to);
                textToTranslate = ArgsExtractor.removeFirstWord(textToTranslate);
            } else {
                toCode = fromCode;
            }
        } else {
            fromCode = translationService.recognizeLanguage(Arrays.asList(textToTranslate)).get(0);
            toCode = ofToTranslation(fromCode);
        }
        if (toCode.equals(fromCode)) {
            fromCode = translationService.recognizeLanguage(Arrays.asList(textToTranslate)).get(0);
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
            return "en";
        }
        if (translationLanguages.getRussian().getLanguageName().equals(languageArg)
                || translationLanguages.getRussian().getLanguageShort().equals(languageArg)) {
            return "ru";
        }
        if (translationLanguages.getUkrainian().getLanguageName().equals(languageArg)
                || translationLanguages.getUkrainian().getLanguageShort().equals(languageArg)) {
            return "uk";
        }

        return null;
    }

    public LanguageAndGenderDto getLanguageAndGenderDto(String receivedText) {

        String language, languageCode, gender;
        if (languageAvailability(receivedText)) {
            language = ArgsExtractor.extractWords(receivedText).get(0);
            languageCode = ofSynthesizing(language);
            receivedText = ArgsExtractor.removeFirstWord(receivedText);
        } else {
            languageCode = translationService.recognizeLanguage(
                    Arrays.asList(ArgsExtractor.removeFirstWord(receivedText))).get(0);
        }

        String finalReceivedText = receivedText;
        if (translationLanguages.getGenderList().stream()
                .filter(g -> g.equals(ArgsExtractor.extractWords(finalReceivedText).get(0))).count() > 0) {
            gender = ArgsExtractor.extractWords(receivedText).get(0).toUpperCase();
            receivedText = ArgsExtractor.removeFirstWord(receivedText);
        } else {
            gender = "FEMALE";
        }

        return LanguageAndGenderDto.builder().languageCode(languageCode).gender(gender).synthesizeText(receivedText).build();
    }

    public String getLanguageName(String code) {

        if (translationLanguages.getEnglish().getLanguageShort().equals(code)
                || translationLanguages.getEnglish().getLanguageCode().equals(code)
                || "en".equals(code)) {
            return translationLanguages.getEnglish().getLanguageName();
        }
        if (translationLanguages.getRussian().getLanguageShort().equals(code)
                || translationLanguages.getRussian().getLanguageCode().equals(code)
                || "ru".equals(code)) {
            return translationLanguages.getRussian().getLanguageName();
        }
        if (translationLanguages.getUkrainian().getLanguageShort().equals(code)
                || translationLanguages.getUkrainian().getLanguageCode().equals(code)
                || "uk".equals(code)) {
            return translationLanguages.getUkrainian().getLanguageName();
        }
        return null;
    }
}
