package com.hatiko.bot.telegram.properties;

import com.hatiko.ms.translation.integration.cloud.translation.properties.TranslationLanguageNameProperties;
import com.hatiko.ms.translation.integration.cloud.translation.properties.TranslationRequestProperties;
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
    private Gender gender;

    @Getter
    private List<String> languageNames;
    @Getter
    private List<String> languageCodes;
    @Getter
    private List<String> languageShorts;
    @Getter
    private List<String> genderList;


    @PostConstruct
    private void prepareFields() {

        english = new English();
        english.setLanguageCode(translationRequestProperties.getLanguageCode().getEnglish());
        english.setLanguageName(translationLanguageNameProperties.getEnglish());
        english.setLanguageShort(translationLanguageNameProperties.getEnglishShort());

        russian = new Russian();
        russian.setLanguageCode(translationRequestProperties.getLanguageCode().getRussian());
        russian.setLanguageName(translationLanguageNameProperties.getRussian());
        russian.setLanguageShort(translationLanguageNameProperties.getRussianShort());

        ukrainian = new Ukrainian();
        ukrainian.setLanguageCode(translationRequestProperties.getLanguageCode().getUkrainian());
        ukrainian.setLanguageName(translationLanguageNameProperties.getUkrainian());
        ukrainian.setLanguageShort(translationLanguageNameProperties.getUkrainianShort());

        gender = new Gender();
        gender.setFemale(translationLanguageNameProperties.getGender().getFemale());
        gender.setMale(translationLanguageNameProperties.getGender().getMale());

        languageNames = new ArrayList<>();
        languageNames.add(english.getLanguageName());
        languageNames.add(russian.getLanguageName());
        languageNames.add(ukrainian.getLanguageName());

        languageCodes = new ArrayList<>();
        languageCodes.add(english.getLanguageCode());
        languageCodes.add(russian.getLanguageCode());
        languageCodes.add(ukrainian.getLanguageCode());

        languageShorts = new ArrayList<>();
        languageShorts.add(english.getLanguageShort());
        languageShorts.add(russian.getLanguageShort());
        languageShorts.add(ukrainian.getLanguageShort());

        genderList = new ArrayList<>();
        genderList.add(gender.getMale());
        genderList.add(gender.getFemale());
    }

    @Data
    public static class English {
        private String languageCode;
        private String languageName;
        private String languageShort;
    }

    @Data
    public static class Russian {
        private String languageCode;
        private String languageName;
        private String languageShort;
    }

    @Data
    public static class Ukrainian {
        private String languageCode;
        private String languageName;
        private String languageShort;
    }

    @Data
    public static class Gender {
        private String male;
        private String female;
    }
}