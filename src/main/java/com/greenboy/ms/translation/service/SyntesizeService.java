package com.greenboy.ms.translation.service;

import java.nio.file.Path;

public interface SyntesizeService {
	
	Path synthesizeText(String text, String language, String gender);
	Path synthesizeTextTemp(String text, String language, String gender);
	Boolean deleteTextIfExist(Path PathToFile);
}
