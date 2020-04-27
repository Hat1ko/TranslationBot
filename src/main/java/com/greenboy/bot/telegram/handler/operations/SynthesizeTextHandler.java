package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SynthesizeTextHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final ArgsExtractor argsExtractor;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getSynthesizeText().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

//      TODO: to be processed soon
        String receivedText = update.getMessage().getText();
        String extractedText = argsExtractor.removeFirstWord(receivedText);

        Optional<Integer> messageId = translationBot.sendMessage(chatId,
                String.format("here gonna be your text synthesized | : your text", receivedText));
    }
}
