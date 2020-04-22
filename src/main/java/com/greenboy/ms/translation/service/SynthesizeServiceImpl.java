package com.greenboy.ms.translation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.greenboy.ms.translation.dto.SynthesizeConfig;
import com.greenboy.ms.translation.integration.text.to.speach.dto.AudioConfigDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.InputDTO;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeRequest;
import com.greenboy.ms.translation.integration.text.to.speach.dto.SynthesizeResponse;
import com.greenboy.ms.translation.integration.text.to.speach.dto.VoiceDTO;
import com.greenboy.ms.translation.integration.text.to.speach.properties.TextToSpeechProperties;
import com.greenboy.ms.translation.integration.text.to.speach.rest.SynthesizerCommunicationService;
import com.greenboy.ms.translation.service.syntesize.support.SynthesizeConfigPreparator;
import com.greenboy.ms.translation.service.syntesize.support.Synthesizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public final class SynthesizeServiceImpl implements SynthesizeService {

	private final SynthesizerCommunicationService synthesizerCommunicationService;
	private final TextToSpeechProperties textToSpeechProperties;
	private final Synthesizer synthesizer;
	private final SynthesizeConfigPreparator syntesizeConfigPreparator;

	@Override
	public Path synthesizeText(String text, String language, String gender) {

		log.info("Syntesize text | text : {}, language : {}, gender : {}", text, language, gender);

		InputDTO input = InputDTO.builder().text(text).build();

		AudioConfigDTO audioConfig = AudioConfigDTO.builder().audioEncoding(textToSpeechProperties.getAudioEncoding())
				.build();

		SynthesizeConfig config = syntesizeConfigPreparator.prepareConfig(language, gender);

		VoiceDTO voice = VoiceDTO.builder().languageCode(config.getLanguageCode()).ssmlGender(config.getSsmlGender())
				.name(config.getSynthesizerName()).build();

		SynthesizeRequest request = SynthesizeRequest.builder().audioConfig(audioConfig).input(input).voice(voice)
				.build();

		SynthesizeResponse response = synthesizerCommunicationService.getSynthesizedString(request);

		log.info("Response | audioContent: {}", response.getAudioContent());

		Path pathToFile = synthesizer.synthesize(text, response.getAudioContent());

		return pathToFile;
	}

	@Override
	public Path synthesizeTextTemp(String text, String language, String gender) {

		log.info("Syntesize text temporary | text : {}, language : {}, gender : {}", text, language, gender);

		InputDTO input = InputDTO.builder().text(text).build();

		AudioConfigDTO audioConfig = AudioConfigDTO.builder().audioEncoding(textToSpeechProperties.getAudioEncoding())
				.build();

		SynthesizeConfig config = syntesizeConfigPreparator.prepareConfig(language, gender);

		VoiceDTO voice = VoiceDTO.builder().languageCode(config.getLanguageCode()).ssmlGender(config.getSsmlGender())
				.name(config.getSynthesizerName()).build();

		SynthesizeRequest request = SynthesizeRequest.builder().audioConfig(audioConfig).input(input).voice(voice)
				.build();

		SynthesizeResponse response = synthesizerCommunicationService.getSynthesizedString(request);

		log.info("Response | audioContent: {}", response.getAudioContent());

		Path pathToFile = synthesizer.synthesizeTemp(text, response.getAudioContent());

		return pathToFile;
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
