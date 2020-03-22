package com.greenboy.ms.translation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.greenboy.ms.translation.integration.text.to.speach.dto.AudioConfigDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.InputDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeResponse;
import com.greenboy.ms.translation.integration.text.to.speach.dto.VoiceDTO;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeachProperties;
import com.greenboy.ms.translation.integration.text.to.speach.rest.SyntesizerCommunicationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class SyntesizeServiceImpl implements SyntesizeService {

	private final SyntesizerCommunicationService syntesizerCommunicationService;
	private final TextToSpeachProperties textToSpeachProperties;
	private final Syntesizer syntesizer;

	@Override
	public List<Path> syntesizeText(List<String> texts) {

		List<InputDTO> inputs = texts.stream().map(text -> InputDTO.builder().text(text).build())
				.collect(Collectors.toList());

		AudioConfigDTO audioConfigDTO = AudioConfigDTO.builder()
				.audioEncoding(textToSpeachProperties.getAudioEncoding()).build();

		// TODO: rework putting settings
		VoiceDTO voiceDTO = VoiceDTO.builder().languageCode(textToSpeachProperties.getLanguageCode().getEnglish())
				.name(textToSpeachProperties.getSyntesizerName().getEnglish())
				.ssmlGender(textToSpeachProperties.getSsmlGender().getFemale()).build();

		List<SyntesizeRequest> requests = inputs.stream()
				.map(e -> SyntesizeRequest.builder().input(e).audioConfig(audioConfigDTO).voice(voiceDTO).build())
				.collect(Collectors.toList());

		List<SyntesizeResponse> responses = requests.stream()
				.map(e -> syntesizerCommunicationService.getSyntesizedString(e)).collect(Collectors.toList());

		Map<String, String> files = new HashMap<String, String>();

		for (int i = 0; i < inputs.size(); i++) {
			files.put(texts.get(i), responses.get(i).getAudioContent());
		}

		List<Path> pathsToFiles = files.entrySet().stream().map(e -> syntesizer.syntesize(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		return pathsToFiles;
	}

	@Override
	public List<Path> syntesizeTextTemp(List<String> texts) {

		List<InputDTO> inputs = texts.stream().map(text -> InputDTO.builder().text(text).build())
				.collect(Collectors.toList());

		AudioConfigDTO audioConfigDTO = AudioConfigDTO.builder()
				.audioEncoding(textToSpeachProperties.getAudioEncoding()).build();

		// TODO: rework putting settings
		VoiceDTO voiceDTO = VoiceDTO.builder().languageCode(textToSpeachProperties.getLanguageCode().getEnglish())
				.name(textToSpeachProperties.getSyntesizerName().getEnglish())
				.ssmlGender(textToSpeachProperties.getSsmlGender().getFemale()).build();

		List<SyntesizeRequest> requests = inputs.stream()
				.map(e -> SyntesizeRequest.builder().input(e).audioConfig(audioConfigDTO).voice(voiceDTO).build())
				.collect(Collectors.toList());

		List<SyntesizeResponse> responses = requests.stream()
				.map(e -> syntesizerCommunicationService.getSyntesizedString(e)).collect(Collectors.toList());

		Map<String, String> files = new HashMap<String, String>();

		for (int i = 0; i < inputs.size(); i++) {
			files.put(texts.get(i), responses.get(i).getAudioContent());
		}

		List<Path> pathsToFiles = files.entrySet().stream().map(e -> syntesizer.syntesizeTemp(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		return pathsToFiles;
	}

	@Override
	public Boolean deleteTextIfExist(Path PathToFile) {

		try {
			Files.delete(PathToFile);
		} catch (IOException e) {
			log.error("Error : {}", e.getMessage());
		}

		return Files.exists(PathToFile);
	}
}
