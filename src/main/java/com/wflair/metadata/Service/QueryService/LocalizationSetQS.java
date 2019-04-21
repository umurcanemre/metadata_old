package com.wflair.metadata.Service.QueryService;

import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.LocalizationSet;

public interface LocalizationSetQS {
    LocalizationSet find(String label);

    Optional<LocalizationSet> findIfExists(String label);

    Set<LocalizationSet> findAll();
}