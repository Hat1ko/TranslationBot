package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
import com.greenboy.bot.telegram.service.ArgsExtractor;
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
public class RecognizeLanguageHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final TranslationService translationService;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getRecognizeLanguage().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToRecognize = ArgsExtractor.removeFirstWord(receivedText);
        String language = translationService.recognizeLanguage(Arrays.asList(textToRecognize)).get(0);
        String responseMessage = actionResponse.recognizeLanguage(language);
        Optional<Integer> messageId = translationBot.sendMessage(chatId, responseMessage);
    }
}
