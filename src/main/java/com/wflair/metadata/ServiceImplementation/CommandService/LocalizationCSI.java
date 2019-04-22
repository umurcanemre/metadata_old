package com.wflair.metadata.ServiceImplementation.CommandService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        Localization localization = new Localization(request.getLocalizationLabel(), values);
        return repository.save(localization);
    }

    private Set<LocalizationValue> prepareLocalizationValues(CreateLocalizationRequest request) {
        Map<String, Language> langs = languageQS.findLanguages(request.getLanguageValueMap().keySet());
        Set<LocalizationValue> resp = new HashSet<>();
        for (Entry<String, String> e : request.getLanguageValueMap().entrySet()) {
            if (langs.containsKey(e.getKey())) {
                resp.add(new LocalizationValue(langs.get(e.getKey()), e.getValue()));
            }
        }
        return resp;
    }

    @Override
    public void deleteLocalization(String label) {
        Localization localization = queryService.findLocalization(label);
        repository.delete(localization);
    }

    @Override
    public Localization putLocalization(CreateLocalizationRequest request) {
        Optional<Localization> foundLocalization = queryService
                .findIfLocalizationExists(request.getLocalizationLabel());

        if (foundLocalization.isPresent()) {
            modifyLocalizationValues(foundLocalization.get(), request.getLanguageValueMap());
            return repository.save(foundLocalization.get());
        } else {
            return saveLocalization(request);
        }
    }

    // TODO: better refactor
    public void modifyLocalizationValues(Localization localization, Map<String, String> newValues) {
        List<LocalizationValue> removeList = getValuesToRemove(localization, newValues);
        List<LocalizationValue> addList = new ArrayList<>();
        localization.getValues().removeAll(removeList);
        newValues.entrySet().stream().forEach(e -> {
            Optional<LocalizationValue> value = localization.getValues().stream()
                    .filter(l -> l.getLanguage().getLabel().equals(e.getKey())).findAny();

            if (value.isPresent()) {
                value.get().setValue(e.getValue());
            } else {
                Language lang = languageQS.findLanguage(e.getKey());
                addList.add(new LocalizationValue(lang, e.getValue()));
            }
        });
        localization.getValues().addAll(addList);
    }

    private List<LocalizationValue> getValuesToRemove(Localization localization, Map<String, String> newValues){
        List<LocalizationValue> removeList = new ArrayList<>();
        for(LocalizationValue value : localization.getValues()){
            if(!newValues.containsKey(value.getLanguage().getLabel()))
                removeList.add(value);
        }
        return removeList;
    }
}