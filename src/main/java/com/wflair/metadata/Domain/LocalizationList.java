package com.wflair.metadata.Domain;

import java.util.Set;

import javax.persistence.Id;

import lombok.EqualsAndHashCode;

public class LocalizationList{
    @Id
    String id;
    String label;

    @EqualsAndHashCode.Exclude 
    Set Localization;
}