package com.wflair.metadata.Controller;

import com.wflair.metadata.ResponseObj.LocalizationResponse;
import com.wflair.metadata.Service.QueryService.LocalizationSetQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    @Autowired
    LocalizationSetQS localizationSetQS;

    @GetMapping(value = "/localizationset/{language}/{label}")
    public LocalizationResponse getLocalizationByLanguageAndLabel(@PathVariable String language,
            @PathVariable String label) {
        return localizationSetQS.getLocalizationResponse(label, language);
    }
}