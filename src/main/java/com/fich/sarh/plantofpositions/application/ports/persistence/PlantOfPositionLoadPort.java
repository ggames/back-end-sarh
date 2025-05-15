package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.Optional;

public interface PlantOfPositionLoadPort {

    Optional<PlantOfPosition> loadPlantOfPosition(Long id);
}
