package com.wflair.metadata.Service.QueryService;

import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Language;

public interface LanguageQS {
    Language findLanguage(Long id);

    Language findLanguage(String label);

    Optional<Language> findIfLanguageExists(Language language);

    Language findLanguage(Language language);

    Set<Language> getAll();
}