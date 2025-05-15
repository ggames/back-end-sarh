package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.List;
import java.util.Optional;

public interface PlantOfPositionRetrievePort {

    List<PlantOfPosition> findAllPlantOfPosition();

    Optional<PlantOfPosition> findById(Long id);



}
