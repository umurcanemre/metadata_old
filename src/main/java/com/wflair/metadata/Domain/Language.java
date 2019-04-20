package com.wflair.metadata.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(unique = true)
    private String label;
    private String name;

    public Language(String label, String name) {
        this.label = label.toLowerCase();
        this.name = name.toLowerCase();
    }

    public void setLabel(String label){
        this.label = label.toLowerCase();
    }

    public void updateLanguage(Language newLanguage){
        this.setLabel(newLanguage.getLabel().toLowerCase());
        this.setName(newLanguage.getName().toLowerCase());
    }
}