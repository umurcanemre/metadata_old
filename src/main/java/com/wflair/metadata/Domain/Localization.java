package com.wflair.metadata.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {"label","language_id"}))
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String label;
    @ManyToOne
    Language language;
    @EqualsAndHashCode.Exclude
    String value;

    public Localization(String label, String value, Language language) {
        this.label = label.toLowerCase();
        this.language = language;
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label.toLowerCase();
    }
}