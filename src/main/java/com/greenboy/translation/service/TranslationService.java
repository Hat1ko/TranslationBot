package com.greenboy.translation.service;

import java.util.List;

public interface TranslationService {
	List<String> translateText(List<String> texts, String fromLanguage, String toLanguage);
	List<String> recognizeLanguage(List<String> texts);
}
