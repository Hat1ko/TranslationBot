package com.greenboy.bot.telegram.handler.basic;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.properties.TranslationBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final TranslationBotProperties botProperties;
    private final CommandProperties commandProperties;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getStart().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

//      TODO: to be processed
        String receivedText = update.getMessage().getText();

        Optional<Integer> messageId = translationBot.sendMessage(chatId, String.format("Hello, %s", update.getMessage().getFrom()));
    }
}
