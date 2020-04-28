package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.dto.LanguageAndGenderDto;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import com.greenboy.bot.telegram.service.FieldPreparator;
import com.greenboy.ms.translation.service.SynthesizeService;
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

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getSynthesizeText().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToSynthesize = ArgsExtractor.removeFirstWord(receivedText);
        LanguageAndGenderDto dto = fieldPreparator.getLanguageAndGenderDto(textToSynthesize);
        Path pathToSynthesizedFile = synthesizeService.synthesizeText(dto.getSynthesizeText(),
                dto.getLanguageCode(), dto.getGender());
        Optional<Integer> messageId = translationBot.sendAudio(
                chatId, dto.getSynthesizeText(), pathToSynthesizedFile.toFile());
    }
}
