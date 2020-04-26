package com.greenboy.ms.translation.integration.cloud.translation.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("translation.language")
@Component
public class TranslationLanguageNameProperties {
    private String english;
    private String russian;
    private String ukrainian;
}
