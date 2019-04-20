package com.wflair.metadata.Service.CommandService;

import com.wflair.metadata.Domain.Language;

public interface LanguageCS {
    Language saveLanguage(String label, String name);

    Language putLanguage(Language language);

    void deleteLanguage(Long id);

    void deleteLanguage(String label);
}