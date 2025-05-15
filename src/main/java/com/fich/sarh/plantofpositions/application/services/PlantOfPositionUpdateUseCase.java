package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionUpdateServicePort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrievePort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSavePort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

@UseCase
public class PlantOfPositionUpdateUseCase implements PlantOfPositionUpdateServicePort {

    private final PlantOfPositionRetrievePort retrievePort;

    private final PlantOfPositionSavePort savePort;

    public PlantOfPositionUpdateUseCase(PlantOfPositionRetrievePort retrievePort, PlantOfPositionSavePort savePort) {
        this.retrievePort = retrievePort;
        this.savePort = savePort;
    }


    @Override
    public PlantOfPosition updatePlantOfPosition(Long id, PlantOfPosition command) {
        return retrievePort.findById(id).map(
                plantposition -> {
                       plantposition.setAgentID(command.getAgentID());
                       plantposition.setPointID(command.getPointID());
                       plantposition.setCharacterplantID(command.getCharacterplantID());
                       plantposition.setCurrentStatusID(command.getCurrentStatusID());

                       return savePort.savePlantOfPosition(plantposition);
                }
        ).get();
    }
}
