package com.greenboy.translation.service;

import java.io.File;
import java.util.List;

public interface SyntesizerService {
	
	File syntesizeText(List<String> texts);
	File syntesizeTextTemp(List<String> texts);
	Boolean deleteText(File file);
	Boolean deleteTextTemp(File file);
}
