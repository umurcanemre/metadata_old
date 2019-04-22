package com.wflair.metadata.Controller;

import java.util.Set;

import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Request.CreateLocalizationRequest;
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

    @GetMapping(value = "/localization/{label}")
    public Localization getLocalizationByLabel(@PathVariable String label) {
        return queryService.findLocalization(label);
    }

    @GetMapping(value = "/localization/id/{id}")
    public Localization getLocalizationById(@PathVariable long id) {
        return queryService.findLocalization(id);
    }

    @PostMapping(value = "/localization")
    public Localization saveLocalization(@RequestBody CreateLocalizationRequest request) {
        return commandService.saveLocalization(request);
    }

    @PutMapping(value = "/localization")
    public Localization putLocalization(@RequestBody CreateLocalizationRequest request) {
        return commandService.putLocalization(request);
    }

    @DeleteMapping(value = "/localization/{label}")
    public void deletelocalizationByLabel(@PathVariable String label) {
        commandService.deleteLocalization(label);
    }

    @GetMapping(value = "/localizations")
    public Set<Localization> getAlllocalizations() {
        return queryService.findAllLocalizations();
    }
}