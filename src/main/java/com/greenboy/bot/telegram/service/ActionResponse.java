package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.properties.CommandProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ActionResponse {

    private final CommandProperties commandProperties;

    public String start(String username) {
        String pattern = commandProperties.getStart().getResponse();
        return String.format(pattern, username);
    }

    public String help(String command) {
        if (command.isBlank()) {
            return commandProperties.getHelp().getResponse();
        }
        List<String> commandHelps = Arrays.asList(commandProperties.getHelp().getResponse().split("\n"));
//      TODO: check for exception and send error message
        return commandHelps.stream().filter(help -> help.startsWith(command)).findAny().get();
    }

    public String autoReply() {
        return null;
    }

    public String recognizeLanguage(String textToRecognize, String language) {
        return String.format(commandProperties.getRecognizeLanguage().getResponse(), textToRecognize, language);
    }

    public String translateText() {
        return null;
    }

    public String translateWords() {
        return null;
    }

    public String synthesizeText() {
        return null;
    }

    public String synthesizeWords() {
        return null;
    }

    public String translateAndSynthesize() {
        return null;
    }

    public String error() {
        return "Error";
    }
}
