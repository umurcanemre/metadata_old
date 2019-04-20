package com.wflair.metadata.Service.QueryService;

import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Localization;

public interface LocalizationQS {
    Set<Localization> findAllLocalizations();
    Localization findLocalization(long id);
    Localization findLocalization(String label, String languageLabel);
    Optional<Localization> findIfLocalizationExists(String label, String languageLabel);
}