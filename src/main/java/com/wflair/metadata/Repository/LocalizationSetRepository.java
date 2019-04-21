package com.wflair.metadata.Repository;

import java.util.Optional;

import com.wflair.metadata.Domain.LocalizationSet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationSetRepository extends CrudRepository<LocalizationSet, Long> {
    Optional<LocalizationSet> findByLabel(String label);
}