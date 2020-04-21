package com.greenboy.ms.translation.service.syntesize.support;

import java.nio.file.Path;

public interface Synthesizer {
	Path synthesize(String text, String audioContent);
	Path synthesizeTemp(String text, String audioContent);
}
