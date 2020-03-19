package com.greenboy.translation.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SyntesizerServiceImpl implements SyntesizerService {

	@Override
	public File syntesizeText(List<String> texts) {

		return null;
	}

	@Override
	public File syntesizeTextTemp(List<String> texts) {

		return null;
	}

	@Override
	public Boolean deleteText(File file) {
		
		return null;
	}

	@Override
	public Boolean deleteTextTemp(File file) {
		
		return null;
	}
	
}
