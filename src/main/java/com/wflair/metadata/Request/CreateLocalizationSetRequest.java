package com.wflair.metadata.Request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateLocalizationSetRequest{
    String label;
    List<String> localizationLabels;
}