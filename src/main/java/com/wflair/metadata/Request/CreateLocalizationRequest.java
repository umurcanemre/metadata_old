package com.wflair.metadata.Request;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateLocalizationRequest {
    String localizationLabel;
    Map<String,String> languageValueMap;
}