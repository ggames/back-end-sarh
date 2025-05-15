package com.fich.sarh.Planthistory.application.ports.entrypoint.api;

import com.fich.sarh.Planthistory.domain.model.PlantHistory;

import java.util.List;
import java.util.Optional;

public interface PlantHistoryRetrieveServicePort {

    List<PlantHistory> getAllPlantHistory();

    Optional<PlantHistory> findById(Long id);
}
