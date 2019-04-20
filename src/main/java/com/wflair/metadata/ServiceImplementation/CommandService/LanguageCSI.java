package com.wflair.metadata.ServiceImplementation.CommandService;

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
        Language existingLanguage = findExistingLanguage(language);

        if (existingLanguage != null) {
            existingLanguage.updateLanguage(language);
            return repository.save(existingLanguage);
        } else {
            Language newLanguage = new Language(language.getLabel(), language.getName());
            return repository.save(newLanguage);
        }
    }

    private Language findExistingLanguage(Language language) {
        Language existingLanguage;
        if (language.getId().compareTo(Integer.toUnsignedLong(0)) > 0) {
            existingLanguage = queryService.findLanguage(language.getId());
        } else {
            existingLanguage = queryService.findLanguage(language.getLabel());
        }
        return existingLanguage;
    }

}