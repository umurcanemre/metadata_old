package com.wflair.metadata.ServiceImplementation.CommandService;

import java.util.Optional;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Repository.LocalizationRepository;
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
    public Localization saveLocalization(String label, String value, String languageLabel) {
        Language lang = languageQS.findLanguage(languageLabel);
        Localization localization = new Localization(label, value, lang);
        return repository.save(localization);
    }

    @Override
    public Localization putLocalization(Localization localization) {
        Optional<Localization> foundLocalization = queryService.findIfLocalizationExists(localization.getLabel(), localization.getLanguage().getLabel());
        if (foundLocalization.isPresent()){
            return updateLocalization(foundLocalization.get(),localization);
        }
        else {
            return saveLocalization(localization.getLabel(), localization.getValue(),
                    localization.getLanguage().getLabel());
        }
    }

    private Localization updateLocalization(Localization existingLocalization, Localization localization) {
        Language newLanguage = languageQS.findLanguage(localization.getLanguage());

        existingLocalization.setLabel(localization.getLabel());
        existingLocalization.setLanguage(newLanguage);
        existingLocalization.setValue(localization.getValue());
        return repository.save(existingLocalization);
    }

    @Override
    public void deleteLocalization(String label, String langLabel) {
        Localization localization = queryService.findLocalization(label, langLabel);
        repository.delete(localization);
    }

}