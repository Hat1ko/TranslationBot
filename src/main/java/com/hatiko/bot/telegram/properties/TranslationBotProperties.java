package com.hatiko.bot.telegram.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("telegram.bot")
@Component
public class TranslationBotProperties {
    private String token;
    private String username;
    private Integer creatorId;
}
