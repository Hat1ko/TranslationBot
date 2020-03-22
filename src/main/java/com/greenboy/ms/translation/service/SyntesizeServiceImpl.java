package com.greenboy.ms.translation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.greenboy.ms.translation.dto.SyntesizeEntity;
import com.greenboy.ms.translation.integration.text.to.speach.dto.AudioConfigDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.InputDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SyntesizeResponse;
import com.greenboy.ms.translation.integration.text.to.speach.dto.VoiceDTO;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeachProperties;
import com.greenboy.ms.translation.integration.text.to.speach.rest.SyntesizerCommunicationService;
import com.greenboy.ms.translation.service.syntesize.support.SyntesizeConfigPreparator;
import com.greenboy.ms.translation.service.syntesize.support.Syntesizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class SyntesizeServiceImpl implements SyntesizeService {

	private final SyntesizerCommunicationService syntesizerCommunicationService;
	private final TextToSpeachProperties textToSpeachProperties;
	private final Syntesizer syntesizer;
	private final SyntesizeConfigPreparator syntesizeConfigPreparator;

//TODO: rewrite methods
	@Override
	public Path syntesizeText(String text, String language, String gender) {

//		List<InputDTO> inputs = texts.stream().map(text -> InputDTO.builder().text(text).build())
//				.collect(Collectors.toList());

		InputDTO input = InputDTO.builder().text(text).build();

		AudioConfigDTO audioConfig = AudioConfigDTO.builder()
				.audioEncoding(textToSpeachProperties.getAudioEncoding()).build();

		SyntesizeEntity config = syntesizeConfigPreparator.prepareConfig(language, gender);

		// TODO: rework putting settings
		VoiceDTO voice = VoiceDTO.builder().languageCode(config.getLanguageCode()).ssmlGender(config.getSsmlGender())
				.name(config.getSyntesizerName()).build();

//		List<SyntesizeRequest> requests = inputs.stream()
//				.map(e -> SyntesizeRequest.builder().input(e).audioConfig(audioConfigDTO).voice(voiceDTO).build())
//				.collect(Collectors.toList());

		SyntesizeRequest request = SyntesizeRequest.builder().audioConfig(audioConfig).input(input).voice(voice).build();
		
//		List<SyntesizeResponse> responses = requests.stream()
//				.map(e -> syntesizerCommunicationService.getSyntesizedString(e)).collect(Collectors.toList());

		SyntesizeResponse response = syntesizerCommunicationService.getSyntesizedString(request);
		
//		Map<String, String> files = new HashMap<String, String>();
//
//		for (int i = 0; i < inputs.size(); i++) {
//			files.put(texts.get(i), responses.get(i).getAudioContent());
//		}

//		List<Path> pathsToFiles = files.entrySet().stream().map(e -> syntesizer.syntesize(e.getKey(), e.getValue()))
//				.collect(Collectors.toList());

		Path pathToFile = syntesizer.syntesize(text, response.getAudioContent());
		
		return pathToFile;
	}

	@Override
	public Path syntesizeTextTemp(String text, String language, String gender) {


//		List<InputDTO> inputs = texts.stream().map(text -> InputDTO.builder().text(text).build())
//				.collect(Collectors.toList());

		InputDTO input = InputDTO.builder().text(text).build();

		AudioConfigDTO audioConfig = AudioConfigDTO.builder()
				.audioEncoding(textToSpeachProperties.getAudioEncoding()).build();

		SyntesizeEntity config = syntesizeConfigPreparator.prepareConfig(language, gender);

		// TODO: rework putting settings
		VoiceDTO voice = VoiceDTO.builder().languageCode(config.getLanguageCode()).ssmlGender(config.getSsmlGender())
				.name(config.getSyntesizerName()).build();

//		List<SyntesizeRequest> requests = inputs.stream()
//				.map(e -> SyntesizeRequest.builder().input(e).audioConfig(audioConfigDTO).voice(voiceDTO).build())
//				.collect(Collectors.toList());

		SyntesizeRequest request = SyntesizeRequest.builder().audioConfig(audioConfig).input(input).voice(voice).build();
		
//		List<SyntesizeResponse> responses = requests.stream()
//				.map(e -> syntesizerCommunicationService.getSyntesizedString(e)).collect(Collectors.toList());

		SyntesizeResponse response = syntesizerCommunicationService.getSyntesizedString(request);
		
//		Map<String, String> files = new HashMap<String, String>();
//
//		for (int i = 0; i < inputs.size(); i++) {
//			files.put(texts.get(i), responses.get(i).getAudioContent());
//		}

//		List<Path> pathsToFiles = files.entrySet().stream().map(e -> syntesizer.syntesize(e.getKey(), e.getValue()))
//				.collect(Collectors.toList());

		Path pathToFile = syntesizer.syntesizeTemp(text, response.getAudioContent());
		
		return pathToFile;	}

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
