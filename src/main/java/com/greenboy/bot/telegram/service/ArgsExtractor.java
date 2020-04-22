package com.greenboy.bot.telegram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArgsExtractor {
    public String[] extract(String receivedCommand){
        return receivedCommand.substring(receivedCommand.indexOf(" ", 0) + 1).split(" ");
    }
}
