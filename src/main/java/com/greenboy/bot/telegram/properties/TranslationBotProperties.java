package com.greenboy.bot.telegram.properties;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@ConfigurationProperties("telegram.bot")
@Component
public class TranslationBotProperties {
    private final String token;
    private final String username;
}
