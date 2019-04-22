package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Request.CreateLocalizationRequest;
import com.wflair.metadata.Domain.Localization;

public interface LocalizationCS {
    Localization saveLocalization(CreateLocalizationRequest request);

    void deleteLocalization(String label);

	Localization putLocalization(CreateLocalizationRequest request);
}