package com.wflair.metadata.Service.QueryService;

import com.wflair.metadata.Domain.Language;

public interface LanguageQS{
    Language findLanguage(Long id);
    Language findLanguage(String label);
}