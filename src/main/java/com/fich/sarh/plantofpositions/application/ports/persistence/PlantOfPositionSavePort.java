package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

public interface PlantOfPositionSavePort {

    PlantOfPosition savePlantOfPosition(PlantOfPosition plantposition);
}
