package com.greenboy.bot.telegram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LanguageAndGenderDto {
    private String languageCode;
    private String gender;
    private String synthesizeText;
}
