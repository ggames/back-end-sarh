package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.List;
import java.util.Optional;

public interface PlantOfPositionRetrieveServicePort {

    List<PlantOfPosition> getAllPlantOfPositions();

    Optional<PlantOfPosition> findById(Long id);

}
