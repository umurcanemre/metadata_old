package com.wflair.metadata.ResponseObj;

import java.util.HashMap;
import java.util.Map;

import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Domain.LocalizationValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizationResponse {
    String languageLabel;
    Map<String, String> labelValueMap;

    public LocalizationResponse(String langLabel, LocalizationSet set) {
        languageLabel = langLabel;
        labelValueMap = new HashMap<>();
        set.getValueSet().forEach(v -> {
            LocalizationValue value = v.getValueByLanguage(langLabel);
            if (value != null) {
                labelValueMap.put(v.getLabel(), value.getValue());
            }
        });
    }
}