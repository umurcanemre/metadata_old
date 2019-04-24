package com.wflair.metadata.ServiceImplementation.QueryService;

import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Sets;
import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Repository.LocalizationSetRepository;
import com.wflair.metadata.ResponseObj.LocalizationResponse;
import com.wflair.metadata.Service.QueryService.LanguageQS;
import com.wflair.metadata.Service.QueryService.LocalizationSetQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LocalizationSetQSI implements LocalizationSetQS {
    @Autowired
    LocalizationSetRepository repository;
    @Autowired
    LanguageQS languageQS;

    @Override
    public LocalizationSet find(String label) {
        return checkQuery(findIfExists(label));
    }

    @Override
    public Optional<LocalizationSet> findIfExists(String label) {
        return repository.findByLabel(label);
    }

    @Override
    public Set<LocalizationSet> findAll() {
        return Sets.newHashSet(repository.findAll());
    }

    private LocalizationSet checkQuery(Optional<LocalizationSet> foundLocalizationSet) {
        if (foundLocalizationSet.isPresent())
            return foundLocalizationSet.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localization Not Found");
    }

    @Override
    public LocalizationResponse getLocalizationResponse(String setLabel, String lang) {
        LocalizationSet set = find(setLabel);
        return new LocalizationResponse(lang, set);
    }

}