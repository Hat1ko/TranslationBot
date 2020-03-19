package com.greenboy.translation.service;

import java.io.File;
import java.nio.file.Path;

public interface Syntesizer {
	Path syntesize(String text, String audioContent);
}
