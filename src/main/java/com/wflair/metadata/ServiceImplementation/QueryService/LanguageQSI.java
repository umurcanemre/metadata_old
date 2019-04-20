package com.wflair.metadata.ServiceImplementation.QueryService;

import java.util.Optional;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Repository.LanguageRepository;
import com.wflair.metadata.Service.QueryService.LanguageQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageQSI implements LanguageQS {
    @Autowired
    LanguageRepository repository;

    @Override
    public Language findLanguage(Long id) {
        Optional<Language> language = repository.findById(id);
        return language.isPresent() ? language.get() : null;
    }

    @Override
    public Language findLanguage(String label) {
        Optional<Language> language = repository.findByLabel(label.toLowerCase());
        return language.isPresent() ? language.get() : null;
    }

}