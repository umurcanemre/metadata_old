package com.wflair.metadata.Controller;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Service.CommandService.LanguageCS;
import com.wflair.metadata.Service.QueryService.LanguageQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    @Autowired
    LanguageCS commandService;
    @Autowired
    LanguageQS queryService;

    @PostMapping(value = "/language")
    public Language saveLanguage(@RequestBody Language newLanguage) {
        return commandService.saveLanguage(newLanguage.getLabel(), newLanguage.getName());
    }

    @PutMapping(value = "/language")
    public Language putLanguage(@RequestBody Language newLanguage) {
        return commandService.putLanguage(newLanguage);
    }

    @GetMapping(value = "/language")
    public Language getLanguageById(@RequestParam(value = "id") long id) {
        return queryService.findLanguage(id);
    }

    @GetMapping(value = "/language")
    public Language getLanguageByLabel(@RequestParam(value = "label") String label) {
        return queryService.findLanguage(label);
    }

    @DeleteMapping(value = "/language")
    public void deleteLanguageById(@RequestParam(value = "id") long id) {
        commandService.deleteLanguage(id);
    }

    @DeleteMapping(value = "/language")
    public void deleteLanguageByLabel(@RequestParam(value = "label") String label) {
        commandService.deleteLanguage(label);
    }
}