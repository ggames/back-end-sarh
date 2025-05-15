package com.fich.sarh.Planthistory.application.ports.entrypoint.api;

import com.fich.sarh.Planthistory.domain.model.PlantHistory;

public interface PlantHistorySaveServicePort {

    PlantHistory savePlantHistory(PlantHistory plantHistory);
}
