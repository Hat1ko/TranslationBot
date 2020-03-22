package com.greenboy.ms.translation.service;

import java.nio.file.Path;

public interface SyntesizeService {
	
	Path syntesizeText(String text, String language, String gender);
	Path syntesizeTextTemp(String text, String language, String gender);
	Boolean deleteTextIfExist(Path PathToFile);
}
