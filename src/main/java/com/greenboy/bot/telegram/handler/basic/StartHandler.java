package com.greenboy.bot.telegram.handler.basic;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
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
    private final CommandProperties commandProperties;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getStart().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        log.info("Handling {} in chat = {}", commandProperties.getStart().getRequest(), chatId);

        String responseMessage = actionResponse.start(update.getMessage().getFrom().getFirstName());
        Optional<Integer> messageId = translationBot.sendMessage(chatId, responseMessage);
        messageId = translationBot.sendMessage(chatId, actionResponse.help(update.getMessage().getText()));
    }
}
