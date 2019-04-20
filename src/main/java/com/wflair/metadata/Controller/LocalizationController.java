package com.wflair.metadata.Controller;

import java.util.Set;

import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Service.CommandService.LocalizationCS;
import com.wflair.metadata.Service.QueryService.LocalizationQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalizationController {
    @Autowired
    LocalizationQS queryService;
    @Autowired
    LocalizationCS commandService;

    @GetMapping(value =  "/localization/{langLabel}/{label}")
    public Localization getLocalizationByLabel(@PathVariable String langLabel, @PathVariable String label){
        return queryService.findLocalization(label,langLabel);
    }

    @GetMapping(value =  "/localization/id/{id}")
    public Localization getLocalizationById(@PathVariable long id){
        return queryService.findLocalization(id);
    }

    @PostMapping(value = "/localization")
    public Localization saveLocalization(@RequestBody Localization newLocalization) {
        return commandService.saveLocalization(newLocalization.getLabel(), newLocalization.getValue(), newLocalization.getLanguage().getLabel());
    }

    @PutMapping(value = "/localization")
    public Localization putLocalization(@RequestBody Localization newLocalization) {
        return commandService.putLocalization(newLocalization);
    }

    @DeleteMapping(value = "/localization/{langLabel}/{label}")
    public void deletelocalizationByLabel(@PathVariable String langLabel, @PathVariable String label) {
        commandService.deleteLocalization(label, langLabel);
    }

    @GetMapping(value = "/localizations")
    public Set<Localization> getAlllocalizations(){
        return queryService.findAllLocalizations();
    }

}