package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Request.LocalizationSetRequest;

public interface LocalizationSetCS {
    LocalizationSet save(LocalizationSetRequest request);

    LocalizationSet addLocalization(LocalizationSetRequest request);

    LocalizationSet removeLocalization(LocalizationSetRequest request);

    void deleteLocalizationSet(String label);
}