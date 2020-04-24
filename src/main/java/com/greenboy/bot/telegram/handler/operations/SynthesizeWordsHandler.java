package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.properties.TranslationBotProperties;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SynthesizeWordsHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final TranslationBotProperties botProperties;
    private final CommandProperties commandProperties;
    private final ArgsExtractor argsExtractor;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getSynthesizeWords().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

//      TODO: to be processed soon
        String receivedMessage = update.getMessage().getText();
        String[] words = argsExtractor.extractWords(receivedMessage);

        Optional<Integer> messageId = translationBot.sendMessage(chatId, "Synthesized words must be in next messages");
        for (String word : words) {
            messageId = translationBot.sendMessage(chatId, String.format("here should be synthesized word : %s", word));
        }
    }
}
