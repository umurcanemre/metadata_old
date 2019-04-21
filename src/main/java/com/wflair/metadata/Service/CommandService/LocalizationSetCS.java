package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Request.CreateLocalizationSetRequest;

public interface LocalizationSetCS {
    LocalizationSet save(CreateLocalizationSetRequest request);

    void deleteLocalizationSet(String label);
}