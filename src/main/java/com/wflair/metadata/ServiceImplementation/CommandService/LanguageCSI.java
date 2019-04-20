package com.wflair.metadata.ServiceImplementation.CommandService;

import java.util.Optional;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Repository.LanguageRepository;
import com.wflair.metadata.Service.CommandService.LanguageCS;
import com.wflair.metadata.Service.QueryService.LanguageQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageCSI implements LanguageCS {
    @Autowired
    LanguageRepository repository;
    @Autowired
    LanguageQS queryService;

    @Override
    public Language saveLanguage(String label, String name) {
        Language lang = new Language(label, name);
        return repository.save(lang);
    }

    @Override
    public Language putLanguage(Language language) {
        Optional<Language> existingLanguage = queryService.findIfLanguageExists(language);
        if (existingLanguage.isPresent()) {
            existingLanguage.get().updateLanguage(language);
            return repository.save(existingLanguage.get());
        } else {
            Language newLanguage = new Language(language.getLabel(), language.getName());
            return repository.save(newLanguage);
        }
    }

    @Override
    public void deleteLanguage(Long id) {
        Language language = queryService.findLanguage(id);
        repository.delete(language);
    }

    @Override
    public void deleteLanguage(String label) {
        Language language = queryService.findLanguage(label);
        repository.delete(language);
    }

}