package com.greenboy.ms.translation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SyntesizeEntity {

	private String languageCode;
	private String ssmlGender;
	private String syntesizerName;
}
