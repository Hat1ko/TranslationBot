package com.greenboy.ms.translation.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyntesizeTextRequest {
	
	private String language;
	private String gender;
	private String content;
}
