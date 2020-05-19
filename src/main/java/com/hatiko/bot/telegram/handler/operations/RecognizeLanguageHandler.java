package com.hatiko.bot.telegram.handler.operations;

import com.hatiko.bot.telegram.TranslationBot;
import com.hatiko.bot.telegram.handler.TelegramUpdateHandler;
import com.hatiko.bot.telegram.properties.CommandProperties;
import com.hatiko.bot.telegram.service.ActionResponse;
import com.hatiko.bot.telegram.service.ArgsExtractor;
import com.hatiko.bot.telegram.service.FieldPreparator;
import com.hatiko.ms.translation.service.TranslationService;
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
    private final FieldPreparator fieldPreparator;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getRecognizeLanguage().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToRecognize = ArgsExtractor.removeFirstWord(receivedText);

        log.info("Handling {} in chat = {}", commandProperties.getRecognizeLanguage().getRequest(), chatId);

        if (textToRecognize.equals(receivedText)) {
            Optional<Integer> messageId = translationBot.sendMessage(chatId, actionResponse.autoReply());
            return;
        }

        String language = translationService.recognizeLanguage(Arrays.asList(textToRecognize)).get(0);
        String responseMessage = actionResponse.recognizeLanguage(fieldPreparator.getLanguageName(language));
        Optional<Integer> messageId = translationBot.sendMessage(chatId, responseMessage);
    }
}
