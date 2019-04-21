package com.wflair.metadata.Domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {"label"}))
public class LocalizationSet{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;
    String label;

    @EqualsAndHashCode.Exclude 
    @OneToMany
    Set<Localization> valueSet;

    public LocalizationSet(String label, Set<Localization> localizations){
        this.label = label.toLowerCase();
        this.valueSet = localizations;
    }

    public void setLabel(String label){
        this.label = label.toLowerCase();
    }
}