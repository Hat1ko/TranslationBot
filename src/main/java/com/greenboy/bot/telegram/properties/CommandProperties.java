package com.greenboy.bot.telegram.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@ConfigurationProperties("telegram.bot.command")
@Component
public class CommandProperties {
    private String start;
    private String help;
}
