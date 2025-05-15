package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

public interface PlantOfPositionUpdateServicePort {

    PlantOfPosition updatePlantOfPosition(Long id, PlantOfPosition command);


}
