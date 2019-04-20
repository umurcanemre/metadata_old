package com.wflair.metadata.Repository;

import java.util.Optional;

import com.wflair.metadata.Domain.Language;
import com.wflair.metadata.Domain.Localization;

import org.springframework.data.repository.CrudRepository;

public interface LocalizationRepository extends CrudRepository<Localization, Long> {
    Optional<Localization> findByLabelAndLanguage(String label,Language lang);
}