package com.wflair.metadata.ServiceImplementation.CommandService;

import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Repository.LocalizationSetRepository;
import com.wflair.metadata.Request.CreateLocalizationSetRequest;
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
    public LocalizationSet save(CreateLocalizationSetRequest request) {
        return repository.save(new LocalizationSet(request.getLabel(),
                localizationQS.findLocalizations(request.getLocalizationLabels())));
    }

    @Override
    public void deleteLocalizationSet(String label) {
        repository.delete(queryService.find(label));
    }

}