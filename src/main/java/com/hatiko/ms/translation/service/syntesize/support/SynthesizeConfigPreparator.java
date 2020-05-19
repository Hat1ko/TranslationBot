package com.hatiko.ms.translation.service.syntesize.support;

import com.hatiko.ms.translation.dto.SynthesizeConfig;

public interface SynthesizeConfigPreparator {

	SynthesizeConfig prepareConfig(String language, String gender);
}
