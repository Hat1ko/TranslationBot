package com.greenboy.bot.telegram;

import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.TranslationBotProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
public class TranslationBot extends TelegramLongPollingBot {

    private final TranslationBotProperties botProperties;
    private final List<TelegramUpdateHandler> telegramUpdateHandlers;

    @Autowired
    @Lazy
    public TranslationBot(TranslationBotProperties botProperties, List<TelegramUpdateHandler> telegramUpdateHandlers) {
        this.botProperties = botProperties;
        this.telegramUpdateHandlers = telegramUpdateHandlers;
    }

    @Override
    public void onUpdateReceived(Update update) {
        for (TelegramUpdateHandler handler : telegramUpdateHandlers) {
            handler.handle(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}
