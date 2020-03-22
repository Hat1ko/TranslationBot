package com.greenboy.ms.translation.service.syntesize.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SyntesizerImpl implements Syntesizer {

	@Override
	public Path syntesize(String text, String audioContent) {

		byte[] decodedAudioContent = Base64.getDecoder().decode(audioContent);

		Path pathToSound = Path.of(String.format("audio"))
				.resolve(String.format("%s.mp3", text.substring(0, Math.min(text.length(), 10))));

		if (Files.exists(pathToSound)) {
			try {
				Files.delete(pathToSound);
			} catch (IOException e) {
				log.error("Error : {}", e.getMessage());
			}
		}

		Path audioFile = null;

		try {
			audioFile = Files.createFile(pathToSound);
			Files.write(audioFile, decodedAudioContent, StandardOpenOption.APPEND);
		} catch (IOException e) {
			log.error("Error : {}", e.getMessage());
		}

		return audioFile;
	}

	@Override
	public Path syntesizeTemp(String text, String audioContent) {

		byte[] decodedAudioContent = Base64.getDecoder().decode(audioContent);

		Path pathToSound = null;
		
		try {
			pathToSound = Files.createTempDirectory("audio")
					.resolve(String.format("%s.mp3", text.substring(0, Math.min(text.length(), 10))));
		} catch (IOException e) {
			log.error("Error : {}", e.getMessage());
		}

		if(pathToSound == null) {
			return null;
		}
		
		if (Files.exists(pathToSound)) {
			try {
				Files.delete(pathToSound);
			} catch (IOException e) {
				log.error("Error : {}", e.getMessage());
			}
		}

		Path audioFile = null;

		try {
			audioFile = Files.createFile(pathToSound);
			Files.write(audioFile, decodedAudioContent, StandardOpenOption.APPEND);
		} catch (IOException e) {
			log.error("Error : {}", e.getMessage());
		}

		return audioFile;
	}
}
