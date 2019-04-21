package com.wflair.metadata.ServiceImplementation.CommandService;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Domain.LocalizationValue;
import com.wflair.metadata.Repository.LocalizationRepository;
import com.wflair.metadata.Request.CreateLocalizationRequest;
import com.wflair.metadata.Service.CommandService.LocalizationCS;
import com.wflair.metadata.Service.QueryService.LanguageQS;
import com.wflair.metadata.Service.QueryService.LocalizationQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizationCSI implements LocalizationCS {
    @Autowired
    LocalizationQS queryService;
    @Autowired
    LanguageQS languageQS;
    @Autowired
    LocalizationRepository repository;

    @Override
    public Localization saveLocalization(CreateLocalizationRequest request) {
        Set<LocalizationValue> values = prepareLocalizationValues(request);
        Localization localization = new Localization(request.getLocalizationLabel(),values);
        return repository.save(localization);
    }

    private Set<LocalizationValue> prepareLocalizationValues(CreateLocalizationRequest request){
        Map<String,Language> langs = languageQS.findLanguages(request.getLanguageValueMap().keySet());
        Set<LocalizationValue> resp = new HashSet<>();
        for(Entry<String,String> e : request.getLanguageValueMap().entrySet()){
            if(langs.containsKey(e.getKey())){
                resp.add(new LocalizationValue(langs.get(e.getKey()),e.getValue()));
            }
        }
        return resp;
    }

    @Override
    public Localization putLocalization(Localization localization) {
        Optional<Localization> foundLocalization = queryService.findIfLocalizationExists(localization.getLabel());
        if (foundLocalization.isPresent()){
            return updateLocalization(foundLocalization.get(),localization);
        }
        else {
            return updateLocalization(new Localization(),localization);
        }
    }

    private Localization updateLocalization(Localization existingLocalization, Localization localization) {
        existingLocalization.setLabel(localization.getLabel());
        existingLocalization.setValues(localization.getValues());
        return repository.save(existingLocalization);
    }

    @Override
    public void deleteLocalization(String label) {
        Localization localization = queryService.findLocalization(label);
        repository.delete(localization);
    }

}