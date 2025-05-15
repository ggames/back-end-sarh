package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionRetrieveServicePort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrievePort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantOfPositionRetrieveUseCase implements PlantOfPositionRetrieveServicePort {

    private final PlantOfPositionRetrievePort retrievePort;

    public PlantOfPositionRetrieveUseCase(PlantOfPositionRetrievePort retrievePort) {
        this.retrievePort = retrievePort;
    }


    @Override
    public List<PlantOfPosition> getAllPlantOfPositions() {

        return retrievePort.findAllPlantOfPosition();
    }

    @Override
    public Optional<PlantOfPosition> findById(Long id) {

        return retrievePort.findById(id);
    }
}
