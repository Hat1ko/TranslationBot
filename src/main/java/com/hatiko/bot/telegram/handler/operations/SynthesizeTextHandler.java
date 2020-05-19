package com.hatiko.bot.telegram.handler.operations;

import com.hatiko.bot.telegram.TranslationBot;
import com.hatiko.bot.telegram.dto.LanguageAndGenderDto;
import com.hatiko.bot.telegram.handler.TelegramUpdateHandler;
import com.hatiko.bot.telegram.properties.CommandProperties;
import com.hatiko.bot.telegram.service.ActionResponse;
import com.hatiko.bot.telegram.service.ArgsExtractor;
import com.hatiko.bot.telegram.service.FieldPreparator;
import com.hatiko.ms.translation.service.SynthesizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SynthesizeTextHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final SynthesizeService synthesizeService;
    private final FieldPreparator fieldPreparator;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getSynthesizeText().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToSynthesize = ArgsExtractor.removeFirstWord(receivedText);

        log.info("Handling {} in chat = {}", commandProperties.getSynthesizeText().getRequest(), chatId);

        if (textToSynthesize.equals(receivedText)) {
            Optional<Integer> messageId = translationBot.sendMessage(chatId, actionResponse.autoReply());
            return;
        }

        LanguageAndGenderDto dto = fieldPreparator.getLanguageAndGenderDto(textToSynthesize);
        Path pathToSynthesizedFile = synthesizeService.synthesizeText(dto.getSynthesizeText(),
                dto.getLanguageCode(), dto.getGender());
        Optional<Integer> messageId = translationBot.sendAudio(
                chatId, dto.getSynthesizeText(), pathToSynthesizedFile.toFile());
    }
}
