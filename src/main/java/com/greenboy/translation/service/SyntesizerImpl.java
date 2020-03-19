package com.greenboy.translation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SyntesizerImpl implements Syntesizer{

	@Override
	public Path syntesize(String text, String audioContent) {
		
		byte[] decodedAudioContent = Base64.getDecoder().decode(audioContent);
		
		Path pathToSound = Path.of("sound");
		
		pathToSound.resolve(String.format("%s.mp3", text));
		
		Path audioFile = null;
		
		try {
			audioFile = Files.createFile(pathToSound);
			Files.write(audioFile, decodedAudioContent, StandardOpenOption.APPEND);
		} catch (IOException e) {
			log.info("Error : {}", e.getMessage());
		}
		
		return audioFile;
	}

}
