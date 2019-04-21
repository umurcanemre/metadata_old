package com.wflair.metadata.ServiceImplementation.QueryService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Sets;
import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Repository.LocalizationRepository;
import com.wflair.metadata.Service.QueryService.LanguageQS;
import com.wflair.metadata.Service.QueryService.LocalizationQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class LocalizationQSI implements LocalizationQS {
    @Autowired
    LocalizationRepository repository;
    @Autowired
    LanguageQS languageQS;

    @Override
    public Set<Localization> findAllLocalizations() {
        return Sets.newHashSet(repository.findAll());
    }

    @Override
    public Localization findLocalization(long id) {
        Optional<Localization> localization = repository.findById(id);
        return checkLocalizationQuery(localization);
    }

    @Override
    public Localization findLocalization(String label) {
        Optional<Localization> localization = findIfLocalizationExists(label);
        return checkLocalizationQuery(localization);
    }

    private Localization checkLocalizationQuery(Optional<Localization> foundLocalization) {
        if (foundLocalization.isPresent())
            return foundLocalization.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localization Not Found");
    }

    @Override
    public Optional<Localization> findIfLocalizationExists(String label) {
        return repository.findByLabel(label);
    }

    @Override
    public Set<Localization> findLocalizations(List<String> localizationLabels) {
        return repository.findByLabelIn(localizationLabels);
    }

}