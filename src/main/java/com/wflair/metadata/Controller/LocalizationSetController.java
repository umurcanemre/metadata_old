package com.wflair.metadata.Controller;

import java.util.Set;

import com.wflair.metadata.Domain.LocalizationSet;
import com.wflair.metadata.Request.LocalizationSetRequest;
import com.wflair.metadata.Service.CommandService.LocalizationSetCS;
import com.wflair.metadata.Service.QueryService.LocalizationSetQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalizationSetController {
    @Autowired
    LocalizationSetQS queryService;
    @Autowired
    LocalizationSetCS commandService;

    @GetMapping(value = "/localizationset/{label}")
    public LocalizationSet getLocalizationByLabel(@PathVariable String label) {
        return queryService.find(label);
    }

    @PostMapping(value = "/localizationset")
    public LocalizationSet saveLocalization(@RequestBody LocalizationSetRequest request) {
        return commandService.save(request);
    }

    @PostMapping(value = "/localizationset/addlocalization")
    public LocalizationSet addLocalizationToSet(@RequestBody LocalizationSetRequest request) {
        return commandService.addLocalization(request);
    }

    @PostMapping(value = "/localizationset/removelocalization")
    public LocalizationSet removeLocalizationFromSet(@RequestBody LocalizationSetRequest request) {
        return commandService.removeLocalization(request);
    }

    @DeleteMapping(value = "/localizationset/{label}")
    public void deleteLocalizationSetByLabel(@PathVariable String langLabel, @PathVariable String label) {
        commandService.deleteLocalizationSet(label);
    }

    @GetMapping(value = "/localizationsets")
    public Set<LocalizationSet> getAlllocalizationSets() {
        return queryService.findAll();
    }
}