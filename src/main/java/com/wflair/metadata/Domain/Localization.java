package com.wflair.metadata.Domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "label" }))
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;
    String label;
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = false)
    Set<LocalizationValue> values; 

    public Localization(String label,Set<LocalizationValue> values) {
        this.label = label.toLowerCase();
        this.values = values;
    }

    public void setLabel(String label) {
        this.label = label.toLowerCase();
    }
}