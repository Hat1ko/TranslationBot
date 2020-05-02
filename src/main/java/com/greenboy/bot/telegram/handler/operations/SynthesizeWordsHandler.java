package com.greenboy.bot.telegram.handler.operations;

import com.greenboy.bot.telegram.TranslationBot;
import com.greenboy.bot.telegram.dto.LanguageAndGenderDto;
import com.greenboy.bot.telegram.handler.TelegramUpdateHandler;
import com.greenboy.bot.telegram.properties.CommandProperties;
import com.greenboy.bot.telegram.service.ActionResponse;
import com.greenboy.bot.telegram.service.ArgsExtractor;
import com.greenboy.bot.telegram.service.FieldPreparator;
import com.greenboy.ms.translation.service.SynthesizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SynthesizeWordsHandler implements TelegramUpdateHandler {

    private final TranslationBot translationBot;
    private final CommandProperties commandProperties;
    private final FieldPreparator fieldPreparator;
    private final SynthesizeService synthesizeService;
    private final ActionResponse actionResponse;

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(commandProperties.getSynthesizeWords().getRequest())) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String receivedText = update.getMessage().getText();
        String textToSynthesize = ArgsExtractor.removeFirstWord(receivedText);

        log.info("Handling {} in chat = {}", commandProperties.getSynthesizeWords().getRequest(), chatId);

        if (receivedText.equals(textToSynthesize)) {
            Optional<Integer> messageId = translationBot.sendMessage(chatId, actionResponse.autoReply());
            return;
        }

        LanguageAndGenderDto dto = fieldPreparator.getLanguageAndGenderDto(textToSynthesize);
        List<String> wordsToSynthesize = Arrays.asList(dto.getSynthesizeText().split(" "));
        Optional<Integer> messageId;
        for (String word : wordsToSynthesize) {
            messageId = translationBot.sendAudio(
                    chatId, word, synthesizeService.synthesizeText(word,
                            dto.getLanguageCode(), dto.getGender()).toFile());
        }
    }
}
