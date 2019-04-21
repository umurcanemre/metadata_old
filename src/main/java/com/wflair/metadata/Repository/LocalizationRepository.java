package com.wflair.metadata.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Localization;

import org.springframework.data.repository.CrudRepository;

public interface LocalizationRepository extends CrudRepository<Localization, Long> {
    Optional<Localization> findByLabel(String label);

    Set<Localization> findByLabelIn(List<String> labels);
}