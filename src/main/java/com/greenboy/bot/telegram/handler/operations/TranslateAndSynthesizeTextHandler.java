package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.dto.FromToTextDto;
import com.greenboy.bot.telegram.dto.LanguageAndGenderDto;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import com.greenboy.bot.telegram.service.FieldPreparator;
import com.greenboy.ms.translation.service.SynthesizeService;
import com.greenboy.ms.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TranslateAndSynthesizeTextHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final FieldPreparator fieldPreparator;
    private final TranslationService translationService;
    private final SynthesizeService synthesizeService;
    private final CommandProperties commandProperties;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getTranslateAndSynthesizeText().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String text = ArgsExtractor.removeFirstWord(receivedText);

        FromToTextDto codes = fieldPreparator.getFromToTextDto(text);
        LanguageAndGenderDto dto = fieldPreparator.getLanguageAndGenderDto(text);
        String translatedText = translationService.translateText(
                Arrays.asList(codes.getModifiedText()), codes.getFromCode(), codes.getToCode()).get(0);
        Path pathToSynthesized = synthesizeService.synthesizeText(translatedText, codes.getToCode(), dto.getGender());
        Optional<Integer> messageId = translationBot.sendAudio(chatId, translatedText, pathToSynthesized.toFile());
    }
}
