package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import com.greenboy.bot.telegram.properties.TranslationLanguages;
import com.greenboy.bot.telegram.service.ConfigurationFieldPreparator;
import com.greenboy.ms.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TranslateTextHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final ArgsExtractor argsExtractor;
    private final ActionResponse actionResponse;
    private final TranslationLanguages translationLanguages;
    private final TranslationService translationService;
    private final ConfigurationFieldPreparator translateFieldPreparator;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getTranslateText().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToTranslate = argsExtractor.removeFirstWord(receivedText);

        String from, fromCode, to, toCode;
        if (checkForLanguageAvailability(textToTranslate)) {
            from = argsExtractor.extractWords(textToTranslate).get(0);
            fromCode = translateFieldPreparator.of(from);
            textToTranslate = argsExtractor.removeFirstWord(textToTranslate);
            if (checkForLanguageAvailability(textToTranslate)) {
                to = argsExtractor.extractWords(textToTranslate).get(0);
                toCode = translateFieldPreparator.of(to);
                textToTranslate = argsExtractor.removeFirstWord(textToTranslate);
            } else {
                toCode = translateFieldPreparator.ofTo(fromCode);
            }
        } else {
            fromCode = translationService.recognizeLanguage(Arrays.asList(textToTranslate)).get(0);
            toCode = translateFieldPreparator.ofTo(fromCode);
        }

        String translatedText = translationService.translateText(Arrays.asList(textToTranslate), fromCode, toCode).get(0);

        Optional<Integer> messageId = translationBot.sendMessage(chatId, actionResponse.translateText(translatedText));
    }

    private Boolean checkForLanguageAvailability(String textToTranslate) {
        String languageArg = argsExtractor.extractWords(textToTranslate).get(0);
        if (translationLanguages.getLanguageNames().stream().filter(lang -> lang.equals(languageArg)).count() > 0) {
            return Boolean.TRUE;
        }
        if (translationLanguages.getLanguageShorts().stream().filter(lang -> lang.equals(languageArg)).count() > 0) {
            return Boolean.TRUE;
        }
        return false;
    }
}
