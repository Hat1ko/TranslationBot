package com.hatiko.bot.telegram;

import com.hatiko.bot.telegram.handler.TelegramUpdateHandler;
import com.hatiko.bot.telegram.properties.TranslationBotProperties;
import com.hatiko.bot.telegram.service.ActionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TranslationBot extends TelegramLongPollingBot {

    private final TranslationBotProperties botProperties;
    private final List<TelegramUpdateHandler> telegramUpdateHandlers;
    private final ActionResponse actionResponse;

    @Autowired
    @Lazy
    public TranslationBot(TranslationBotProperties botProperties, List<TelegramUpdateHandler> telegramUpdateHandlers, ActionResponse actionResponse) {
        this.botProperties = botProperties;
        this.telegramUpdateHandlers = telegramUpdateHandlers;
        this.actionResponse = actionResponse;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            for (TelegramUpdateHandler handler : telegramUpdateHandlers) {
                handler.handle(update);
            }
        } catch (Exception e) {
            log.error("Error while handling updates : {}", e.getMessage());
            sendMessage(update.getMessage().getChatId(), actionResponse.autoReply());
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

        log.info("Sending message to chatId = {} with text = {}", chatId, text);

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

        log.info("Sending audio to chatId = {} with title = {} and pathToFile = {}",
                chatId, title, wayToAudioFile.toString());

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
