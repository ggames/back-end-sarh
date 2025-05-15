package com.fich.sarh.Planthistory.application.ports.persistence;

import com.fich.sarh.Planthistory.domain.model.PlantHistory;

public interface PlantHistorySavePort {

    PlantHistory savePlantHistory(PlantHistory plantHistory);
}
