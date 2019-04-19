package com.wflair.metadata.Service.QueryService;

import java.util.List;

import com.wflair.metadata.Domain.Localization;

public interface LocalizationQS {
    List<Localization> findAllLocalization();
}