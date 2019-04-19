package com.wflair.metadata.Controller;

import java.util.List;

import com.wflair.metadata.Domain.Localization;
import com.wflair.metadata.Service.QueryService.LocalizationQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalizationController {
    @Autowired
    LocalizationQS queryService;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "This is THE Home page";
    }

    @GetMapping(value = "/localizations", produces = MediaType.TEXT_PLAIN_VALUE)
    public List<Localization> allLocalizations() {
        return queryService.findAllLocalization();
    }

}