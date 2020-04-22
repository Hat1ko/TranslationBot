package com.greenboy.bot.telegram;

import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.TranslationBotProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

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

    public Optional<Integer> sendMessage(Long chatId, String text) {

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            Integer messageId = execute(sendMessage).getMessageId();
            return Optional.of(messageId);
        } catch (TelegramApiException e) {
            log.error("Error while sending text message | Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Integer> sendAudio(Long chatId, String title, File wayToAudioFile) {

        SendAudio sendAudio = new SendAudio();

        sendAudio.setChatId(chatId);
        sendAudio.setAudio(wayToAudioFile);
        sendAudio.setTitle(title);
        sendAudio.disableNotification();

        try {
            Integer messageId = execute(sendAudio).getMessageId();
            return Optional.of(messageId);
        } catch (TelegramApiException e) {
            log.error("Error while sending audio | Error : {}", e.getMessage());
            return Optional.empty();
        }
    }
}
