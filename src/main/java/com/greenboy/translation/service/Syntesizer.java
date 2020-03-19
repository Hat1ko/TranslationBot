package com.greenboy.translation.service;

import java.nio.file.Path;

public interface Syntesizer {
	Path syntesize(String text, String audioContent);
}
