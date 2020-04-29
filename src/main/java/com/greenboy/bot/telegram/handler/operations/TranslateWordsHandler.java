package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.dto.FromToTextDto;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import com.greenboy.bot.telegram.service.FieldPreparator;
import com.greenboy.ms.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TranslateWordsHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final TranslationService translationService;
    private final CommandProperties commandProperties;
    private final FieldPreparator fieldPreparator;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getTranslateWords().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        String receivedText = update.getMessage().getText();
        String args = ArgsExtractor.removeFirstWord(receivedText);
        FromToTextDto codes = fieldPreparator.getFromToTextDto(args);
        List<String> wordsToTranslate = ArgsExtractor.extractWords(codes.getModifiedText());
        List<String> translatedWords = translationService.translateText(
                wordsToTranslate, codes.getFromCode(), codes.getToCode());

        Optional<Integer> messageId;
        if (translatedWords.size() > 0) {
            messageId = translationBot.sendMessage(chatId, "Next are requested to translate words");
            for (int i = 0; i < translatedWords.size(); i++) {
                messageId = translationBot.sendMessage(
                        chatId, String.format("%s : %s", wordsToTranslate.get(i), translatedWords.get(i)));
            }
        } else {
            messageId = translationBot.sendMessage(chatId, actionResponse.error());
        }
    }
}
