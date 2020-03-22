package com.greenboy.ms.translation.service.syntesize.support;

import java.nio.file.Path;

public interface Syntesizer {
	Path syntesize(String text, String audioContent);
	Path syntesizeTemp(String text, String audioContent);
}
