package com.hatiko.bot.telegram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FromToTextDto {
    private String fromCode;
    private String toCode;
    private String modifiedText;
}
