package com.wflair.metadata.Repository;

import java.util.List;

import com.wflair.metadata.Domain.Localization;
import org.springframework.data.repository.CrudRepository;

public interface LocalizationRepository extends CrudRepository<Localization, String> {
    List<Localization> findAll();
}