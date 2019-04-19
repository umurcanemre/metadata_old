package com.wflair.metadata.LocalizationServiceImplementation.QueryService;

import java.util.List;

import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Repository.LocalizationRepository;
import com.wflair.metadata.Service.QueryService.LocalizationQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizationQSI implements LocalizationQS {
    @Autowired
    LocalizationRepository repository;

    @Override
    public List<Localization> findAllLocalization() {
        return repository.findAll();
    }

}