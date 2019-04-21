package com.wflair.metadata.Repository;

import java.util.Optional;
import java.util.Set;

import com.wflair.metadata.Domain.Language;

import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Long>{

	Optional<Language> findByLabel(String label);

	Iterable<Language> findByLabelIn (Set<String> labels);
    
}