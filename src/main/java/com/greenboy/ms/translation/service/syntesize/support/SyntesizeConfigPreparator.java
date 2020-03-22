package com.greenboy.ms.translation.service.syntesize.support;

import com.greenboy.ms.translation.dto.SyntesizeConfig;

public interface SyntesizeConfigPreparator {

	SyntesizeConfig prepareConfig(String language, String gender);
}
