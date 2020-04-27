package com.greenboy.bot.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ArgsExtractor {

    public String extractText(String receivedCommand) {
        return receivedCommand.substring(receivedCommand.indexOf(" ") + 1);
    }

    public List<String> extractWords(String receivedCommand) {
        return Arrays.asList(receivedCommand.split(" "));
    }

    public List<String> extractWordsWithCommand(String receivedCommand) {
        return extractWords(extractText(receivedCommand));
    }
}
