package com.fich.sarh.Planthistory.application.services;

import com.fich.sarh.Planthistory.application.ports.entrypoint.api.PlantHistorySaveServicePort;
import com.fich.sarh.Planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.Planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.UseCase;

@UseCase
public class PlantHistorySaveUseCase implements PlantHistorySaveServicePort {

    private final PlantHistorySavePort savePort;

    public PlantHistorySaveUseCase(PlantHistorySavePort savePort) {
        this.savePort = savePort;
    }

    @Override
    public PlantHistory savePlantHistory(PlantHistory plantHistory) {
        return savePort.savePlantHistory(plantHistory);
    }
}
