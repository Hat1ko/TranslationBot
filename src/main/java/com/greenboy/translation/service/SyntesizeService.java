package com.greenboy.translation.service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface SyntesizeService {
	
	List<Path> syntesizeText(List<String> texts);
	List<Path> syntesizeTextTemp(List<String> texts);
	Boolean deleteText(File file);
	Boolean deleteTextTemp(File file);
}
