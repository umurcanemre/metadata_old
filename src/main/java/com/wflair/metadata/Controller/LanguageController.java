package com.wflair.metadata.Controller;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Service.CommandService.LanguageCS;
import com.wflair.metadata.Service.QueryService.LanguageQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguageController {
    @Autowired
    LanguageCS languageCS;
    @Autowired
    LanguageQS LanguageQS;

    @PostMapping(value = "/language")
    public Language saveLanguage(@RequestBody Language newLanguage) {
        return languageCS.saveLanguage(newLanguage.getLabel(), newLanguage.getName());
    }

    @PutMapping(value = "/language")
    public Language putLanguage(@RequestBody Language newLanguage) {
        return languageCS.putLanguage(newLanguage);
    }

    @GetMapping(value = "/language/{id}")
    public Language getLanguageById(@PathVariable long id) {
        Language foundLang = LanguageQS.findLanguage(id);
        return checkLang(foundLang);
    }

    @GetMapping(value = "/language/")
    public Language getLanguageById(@RequestParam(value = "label") String label) {
        Language foundLang = LanguageQS.findLanguage(label);
        return checkLang(foundLang);
    }

    private Language checkLang(Language foundLang){
        if (foundLang != null)
            return foundLang;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language Not Found");
    }
}