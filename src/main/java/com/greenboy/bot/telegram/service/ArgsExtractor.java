package com.greenboy.bot.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ArgsExtractor {

    public String removeFirstWord(String receivedCommand) {
        return receivedCommand.substring(receivedCommand.indexOf(" ") + 1);
    }

    public List<String> extractWords(String receivedCommand) {
        return Arrays.asList(receivedCommand.split(" "));
    }
}
