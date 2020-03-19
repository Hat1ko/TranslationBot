package com.greenboy.translation.service;

import java.util.List;

public interface TranslationService {
	List<String> translateText(List<String> texts);
	List<String> recognizeLanguage(List<String> texts);
}
