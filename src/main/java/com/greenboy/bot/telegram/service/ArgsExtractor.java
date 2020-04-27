package com.greenboy.bot.telegram.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ArgsExtractor {
    public static String removeFirstWord(String receivedCommand) {
        return receivedCommand.substring(receivedCommand.indexOf(" ") + 1);
    }

    public static List<String> extractWords(String receivedCommand) {
        return Arrays.asList(receivedCommand.split(" "));
    }
}
