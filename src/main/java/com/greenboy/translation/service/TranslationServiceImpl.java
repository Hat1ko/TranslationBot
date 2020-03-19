package com.greenboy.translation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TranslationServiceImpl implements TranslationService{

	@Override
	public List<String> translateText(List<String> texts) {
		
		return null;
	}

	@Override
	public List<String> recognizeLanguage(List<String> texts) {
		
		return null;
	}
}
