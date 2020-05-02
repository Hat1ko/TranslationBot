package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.properties.CommandProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ActionResponse {

    private final CommandProperties commandProperties;

    public String start(String username) {
        String pattern = commandProperties.getStart().getResponse();
        return String.format(pattern, username);
    }

    public String help(String command) {
        if (Arrays.asList(command.split(" ")).size() < 2) {
            return commandProperties.getHelp().getResponse();
        }
        List<String> commandHelps = Arrays.asList(commandProperties.getHelp().getResponse().split("\n"));
        String arg = command.split(" ")[1];
        return commandHelps.stream().filter(help -> help.startsWith(arg)).findAny().get();
    }

    public String autoReply() {
        return commandProperties.getAutoReply().getResponse();
    }

    public String recognizeLanguage(String language) {
        return String.format(commandProperties.getRecognizeLanguage().getResponse(), language);
    }

    public String translateText(String translatedText) {
        return String.format(commandProperties.getTranslateText().getResponse(), translatedText);
    }

    public String translateWords(Integer index, String... words) {
        List<String> responses = Arrays.asList(commandProperties.getTranslateWords().getResponse().split(","));
        switch (index) {
            case 0:
            case 2:
                return responses.get(index);
            case 1:
                return String.format(responses.get(index), words[0], words[1]);
            default:
                return null;
        }
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
