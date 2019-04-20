package com.wflair.metadata.Domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;

@Entity
public class LocalizationSet{
    @Id
    String id;
    String label;

    @EqualsAndHashCode.Exclude 
    @OneToMany
    Set<Localization> valueSet;
}