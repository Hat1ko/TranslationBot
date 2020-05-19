package com.hatiko.bot.telegram.handler.basic;

import com.hatiko.bot.telegram.TranslationBot;
import com.hatiko.bot.telegram.handler.TelegramUpdateHandler;
import com.hatiko.bot.telegram.properties.CommandProperties;
import com.hatiko.bot.telegram.service.ActionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelpHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getHelp().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();

        log.info("Handling {} in chat = {}", commandProperties.getHelp().getRequest(), chatId);

        String responseMessage = actionResponse.help(receivedText);
        Optional<Integer> messageId = translationBot.sendMessage(chatId, responseMessage);
    }
}
