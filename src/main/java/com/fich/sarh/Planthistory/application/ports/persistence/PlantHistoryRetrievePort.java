package com.fich.sarh.Planthistory.application.ports.persistence;

import com.fich.sarh.Planthistory.domain.model.PlantHistory;

import java.util.List;
import java.util.Optional;

public interface PlantHistoryRetrievePort {

    List<PlantHistory> findAllPlantHistory();

    Optional<PlantHistory> findById(Long id);

}
