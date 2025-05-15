package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionSaveServicePort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSavePort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

@UseCase
public class PlantOfPositionSaveUseCase implements PlantOfPositionSaveServicePort {

    private final PlantOfPositionSavePort savePort;

    public PlantOfPositionSaveUseCase(PlantOfPositionSavePort savePort) {
        this.savePort = savePort;
    }

    @Override
    public PlantOfPosition savePlantOfPosition(PlantOfPosition plantposition) {

        return savePort.savePlantOfPosition(plantposition);
    }
}
