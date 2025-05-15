package com.fich.sarh.Planthistory.application.services;

import com.fich.sarh.Planthistory.application.ports.entrypoint.api.PlantHistoryRetrieveServicePort;
import com.fich.sarh.Planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.Planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.Planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.UseCase;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantHistoryRetrieveUseCase implements PlantHistoryRetrieveServicePort {

    private final PlantHistoryRetrievePort retrievePort;


    public PlantHistoryRetrieveUseCase(PlantHistoryRetrievePort retrievePort) {
        this.retrievePort = retrievePort;

    }

    @Override
    public List<PlantHistory> getAllPlantHistory() {
        return retrievePort.findAllPlantHistory();
    }

    @Override
    public Optional<PlantHistory> findById(Long id) {
        return retrievePort.findById(id);
    }
}
