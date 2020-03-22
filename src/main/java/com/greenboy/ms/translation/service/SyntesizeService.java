package com.greenboy.ms.translation.service;

import java.nio.file.Path;
import java.util.List;

public interface SyntesizeService {
	
	List<Path> syntesizeText(List<String> texts);
	List<Path> syntesizeTextTemp(List<String> texts);
	Boolean deleteTextIfExist(Path PathToFile);
}
