package com.greenboy.bot.telegram.handler.basic;

import com.greenboy.TranslationApplication;
import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.TranslationBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final TranslationBotProperties botProperties;

    @Override
    public void handle(Update update) {

    }
}
