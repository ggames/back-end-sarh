package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistoryUpdateServicePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionUpdateApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSaveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantOfPositionUpdateUseCase implements PlantOfPositionUpdateApiPort {

    Logger logger = LoggerFactory.getLogger(PlantOfPositionSaveUseCase.class);
    private final PlantOfPositionRetrieveSpiPort retrievePort;
    private final PlantOfPositionSaveSpiPort savePort;
    private final PositionUpdateServicePort positionUpdateServicePort;
    private final PlantHistoryRetrievePort plantHistoryRetrievePort;
    private final PlantHistorySavePort plantHistorySavePort;
    private final PlantHistoryUpdateServicePort plantHistoryUpdatePort;

    public PlantOfPositionUpdateUseCase(PlantOfPositionRetrieveSpiPort retrievePort, PlantOfPositionSaveSpiPort savePort,
                                        PositionUpdateServicePort positionUpdateServicePort, PlantHistoryRetrievePort plantHistoryRetrievePort, PlantHistorySavePort plantHistorySavePort, PlantHistoryUpdateServicePort plantHistoryUpdatePort) {
        this.retrievePort = retrievePort;
        this.savePort = savePort;
        this.positionUpdateServicePort = positionUpdateServicePort;
        this.plantHistoryRetrievePort = plantHistoryRetrievePort;
        this.plantHistorySavePort = plantHistorySavePort;
        this.plantHistoryUpdatePort = plantHistoryUpdatePort;
    }


    @Override
    public PlantOfPosition updatePlantOfPosition(Long id, PlantOfPositionRequest command) {

        logger.info("UPDATE PLANTA");
        Optional<PlantOfPosition> plantOfPosition = retrievePort.findById(id);

        if (!plantOfPosition.isPresent()) {
            throw new RuntimeException("No existe el cargo seleccionado");
        }

        logger.error("Registro de planta " + command);

        plantOfPosition.get().setCurrentStatusID(command.getCurrentStatusID());

        Position position = updateRegisterPosition(plantOfPosition.get().getPosition(), command.getCurrentStatusID());

        logger.info("FECHA DESDE PLANT HISTORIA " + command.getCurrentStatusID() );

        PlantHistory plantHistory = plantHistoryRetrievePort.fetchTopByPlantIdOrderHistoryIdDesc(plantOfPosition.get().getId());

        logger.info("CANTIDAD DE REGISTROS");

        if(plantHistory == null){
            throw new RuntimeException("No existe el historial planta");
        }



        if (command.getDateTo() != null) {

            plantHistory.setDateTo(command.getDateTo());
            plantHistory.setPlantStatus(command.getCurrentStatusID());
            plantHistoryUpdatePort.updatePlantHistory(plantHistory.getId(), plantHistory);
        }
        if (command.getDateTo() == null) {

            PlantHistory plantHistoriaNew = PlantHistory.builder().plantStatus(command.getCurrentStatusID())
                //    .historyPrev(plantHistory.get().getHistoryCurrent())
                    .plantOfPosition(plantOfPosition.get())
                    .dateFrom(command.getDateFrom())

                     .build();

            plantHistoriaNew = plantHistorySavePort.savePlantHistory(plantHistoriaNew);

        }


        return plantOfPosition.get();


    }

    private Position updateRegisterPosition(Position position, PlantStatus plantStatus) {

        if (plantStatus.equals(PlantStatus.FINALIZADO)) {
            position.setPositionStatus(StatusOfPositions.VACANTE_DEFINITIVA);
        }
        if (plantStatus.equals(PlantStatus.LICENCIA_TRANSITORIA)) {
            position.setPositionStatus(StatusOfPositions.VACANTE_TRANSITORIA);
        }
        if (plantStatus.equals(PlantStatus.OCUPADO_TRANSITORIAMENTE)) {
            position.setPositionStatus(StatusOfPositions.ACTIVO);
        }

        return positionUpdateServicePort.updatePositionByOriginator(position.getId(), position);
    }
}

/*retrievePort.findById(id).map(
                plantposition -> {
                       plantposition.setAgentID(command.getAgentId());
                       plantposition.setPositionID(command.getPositionId());
                       plantposition.setCharacterplantID(command.getCharacterplantID());
                       plantposition.setCurrentStatusID(command.getCurrentStatusID());

                       return savePort.savePlantOfPosition(plantposition);
                }
        ).get(); */