package com.greenboy.bot.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArgsExtractor {

    public String extractText(String receivedCommand) {
        return receivedCommand.substring(receivedCommand.indexOf(" ") + 1);
    }

    public String[] extractWords(String receivedCommand) {
        return receivedCommand.split(" ");
    }

    public String[] extractWordsWithCommand(String receivedCommand) {
        return extractWords(extractText(receivedCommand));
    }
}
