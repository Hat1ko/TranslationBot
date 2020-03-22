package com.greenboy.ms.translation.service.syntesize.support;

import com.greenboy.ms.translation.dto.SyntesizeEntity;

public interface SyntesizeConfigPreparator {

	SyntesizeEntity prepareConfig(String language, String gender);

}
