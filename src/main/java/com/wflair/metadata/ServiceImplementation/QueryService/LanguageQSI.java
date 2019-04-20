package com.wflair.metadata.ServiceImplementation.QueryService;

import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Sets;
import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Repository.LanguageRepository;
import com.wflair.metadata.Service.QueryService.LanguageQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LanguageQSI implements LanguageQS {
    @Autowired
    LanguageRepository repository;

    @Override
    public Language findLanguage(Long id) {
        Optional<Language> language = repository.findById(id);
        return checkLanguageQuery(language);
    }

    @Override
    public Language findLanguage(String label) {
        Optional<Language> language = repository.findByLabel(label.toLowerCase());
        return checkLanguageQuery(language);
    }

    @Override
    public Optional<Language> findIfLanguageExists(Language language) {
        Optional<Language> response = Optional.empty();
        try {
            response = Optional.of(findLanguage(language));
        } catch (ResponseStatusException e) {
            response = Optional.empty();
        }
        return response;
    }

    private Language checkLanguageQuery(Optional<Language> foundLang) {
        if (foundLang.isPresent())
            return foundLang.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language Not Found");
    }

    @Override
    public Language findLanguage(Language language) {
        if (language.getId().compareTo(Integer.toUnsignedLong(0)) > 0) {
            return findLanguage(language.getId());
        } else {
            return findLanguage(language.getLabel());
        }
    }

    @Override
    public Set<Language> getAll() {
        return Sets.newHashSet(repository.findAll());
    }
}