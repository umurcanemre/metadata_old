package com.wflair.metadata.Domain;

import java.util.Optional;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<LocalizationValue> values;

    public Localization(String label, Set<LocalizationValue> values) {
        this.label = label.toLowerCase();
        this.values = values;
    }

    public void setLabel(String label) {
        this.label = label.toLowerCase();
    }

    public LocalizationValue getValueByLanguage(String langLabel) {
        Optional<LocalizationValue> value = this.values.stream()
                .filter(v -> v.getLanguage().getLabel().equals(langLabel)).findAny();
        return value.isPresent() ? value.get() : null;
    }

    public LocalizationValue getValueByLanguage(Language language) {
        return getValueByLanguage(language.getLabel());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Localization))
            return false;

        Localization controlee = (Localization) o;
        if (controlee == this)
            return true;
        if (controlee.getId() != null && controlee.getId().equals(this.id))
            return true;
        if (controlee.getLabel() != null && controlee.getLabel().equals(this.getLabel()))
            return true;

        return false;
    }
}