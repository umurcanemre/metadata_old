package com.wflair.metadata.Service.QueryService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Localization;

public interface LocalizationQS {
    Set<Localization> findAllLocalizations();
    Set<Localization> findLocalizations(List<String> localizationLabels);
    Localization findLocalization(long id);
    Localization findLocalization(String label);
    Optional<Localization> findIfLocalizationExists(String label);
}