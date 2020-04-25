package com.greenboy.bot.telegram.service;

import com.greenboy.bot.telegram.properties.CommandProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ActionResponse {

    private final CommandProperties commandProperties;

    public String start(String username) {
        String pattern = commandProperties.getStart().getResponse();
        return String.format(pattern, username);
    }

    public String help() {
        return null;
    }

    public String autoReply() {
        return null;
    }

    public String recognizeLanguage() {
        return null;
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
}
