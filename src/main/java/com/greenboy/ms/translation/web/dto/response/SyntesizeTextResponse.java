package com.greenboy.ms.translation.web.dto.response;

import java.nio.file.Path;

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
public class SyntesizeTextResponse {
	
	private Path paths;
}
