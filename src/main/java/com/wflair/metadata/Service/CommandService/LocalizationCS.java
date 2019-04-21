package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Request.CreateLocalizationRequest;
import com.wflair.metadata.Domain.Localization;

public interface LocalizationCS {
    Localization saveLocalization(CreateLocalizationRequest request);

    Localization putLocalization(Localization localization);

    void deleteLocalization(String label);
}