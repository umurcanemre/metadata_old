package com.wflair.metadata.ServiceImplementation.CommandService;

import java.util.Set;

import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Repository.LocalizationSetRepository;
import com.wflair.metadata.Request.LocalizationSetRequest;
import com.wflair.metadata.Service.CommandService.LocalizationSetCS;
import com.wflair.metadata.Service.QueryService.LocalizationQS;
import com.wflair.metadata.Service.QueryService.LocalizationSetQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizationSetCSI implements LocalizationSetCS {
    @Autowired
    LocalizationSetRepository repository;
    @Autowired
    LocalizationSetQS queryService;
    @Autowired
    LocalizationQS localizationQS;

    @Override
    public LocalizationSet save(LocalizationSetRequest request) {
        return repository.save(new LocalizationSet(request.getLabel(),
                localizationQS.findLocalizations(request.getLocalizationLabels())));
    }

    @Override
    public void deleteLocalizationSet(String label) {
        repository.delete(queryService.find(label));
    }

    @Override
    public LocalizationSet addLocalization(LocalizationSetRequest request) {
        Set<Localization> localizationsToAdd = localizationQS.findLocalizations(request.getLocalizationLabels());
        LocalizationSet set = queryService.find(request.getLabel());
        set.getValueSet().addAll(localizationsToAdd);
        return repository.save(set);
    }

    @Override
    public LocalizationSet removeLocalization(LocalizationSetRequest request) {
        Set<Localization> localizationsToRemove = localizationQS.findLocalizations(request.getLocalizationLabels());
        LocalizationSet set = queryService.find(request.getLabel());
        set.getValueSet().removeAll(localizationsToRemove);
        return repository.save(set);
    }

}