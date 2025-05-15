package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.Optional;

public interface PlantOfPositionSaveServicePort {

    PlantOfPosition savePlantOfPosition(PlantOfPosition plantposition);
}
