package com.hatiko.bot.telegram.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramUpdateHandler {
    void handle(Update update);
}
