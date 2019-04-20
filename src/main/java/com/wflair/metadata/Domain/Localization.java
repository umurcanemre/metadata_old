package com.wflair.metadata.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Localization{
    @Id
    String id;
    @ManyToOne
    Language language;
    String label;
    @EqualsAndHashCode.Exclude 
    String value;
}