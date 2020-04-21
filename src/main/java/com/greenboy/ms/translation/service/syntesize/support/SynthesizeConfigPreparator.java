package com.greenboy.ms.translation.service.syntesize.support;

import com.greenboy.ms.translation.dto.SynthesizeConfig;

public interface SynthesizeConfigPreparator {

	SynthesizeConfig prepareConfig(String language, String gender);
}
