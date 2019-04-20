package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Domain.Localization;

public interface LocalizationCS {
    Localization saveLocalization(String label, String value, String languageLabel);

    Localization putLocalization(Localization localization);

    void deleteLocalization(String label,String langLabel);
}