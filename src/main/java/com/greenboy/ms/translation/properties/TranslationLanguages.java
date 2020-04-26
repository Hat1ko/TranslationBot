package com.greenboy.ms.translation.properties;

import com.greenboy.ms.translation.integration.cloud.translation.properties.TranslationLanguageNameProperties;
import com.greenboy.ms.translation.integration.cloud.translation.properties.TranslationRequestProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TranslationLanguages {

    private final TranslationRequestProperties translationRequestProperties;
    private final TranslationLanguageNameProperties translationLanguageNameProperties;

    @Getter
    private English english;
    @Getter
    private Russian russian;
    @Getter
    private Ukrainian ukrainian;

    @Getter
    private List<String> languageNames;
    @Getter
    private List<String> languageCodes;


    @PostConstruct
    private void prepareFields() {

        english = new English();
        english.setLanguageCode(translationRequestProperties.getLanguageCode().getEnglish());
        english.setLanguageName(translationLanguageNameProperties.getEnglish());

        russian = new Russian();
        russian.setLanguageCode(translationRequestProperties.getLanguageCode().getRussian());
        russian.setLanguageName(translationLanguageNameProperties.getRussian());

        ukrainian = new Ukrainian();
        ukrainian.setLanguageCode(translationRequestProperties.getLanguageCode().getUkrainian());
        ukrainian.setLanguageName(translationLanguageNameProperties.getUkrainian());

        languageNames = new ArrayList<>();
        languageNames.add(english.getLanguageName());
        languageNames.add(russian.getLanguageName());
        languageNames.add(ukrainian.getLanguageName());

        languageCodes = new ArrayList<>();
        languageCodes.add(english.getLanguageCode());
        languageCodes.add(russian.getLanguageCode());
        languageCodes.add(ukrainian.getLanguageCode());
    }

    @Data
    public static class English {
        private String languageCode;
        private String languageName;
    }

    @Data
    public static class Russian {
        private String languageCode;
        private String languageName;
    }

    @Data
    public static class Ukrainian {
        private String languageCode;
        private String languageName;
    }
}