package com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.Planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.Planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.Planthistory.domain.model.PlantHistory;
import com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.mapper.PlantHistoryMapper;
import com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.repository.PlantHistoryRepository;
import com.fich.sarh.common.PersistenceAdapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class PlantHistoryPersistenceAdapter implements PlantHistoryRetrievePort, PlantHistorySavePort {

    private final PlantHistoryRepository historyRepository;



    public PlantHistoryPersistenceAdapter(PlantHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<PlantHistory> findAllPlantHistory() {
        return  historyRepository.findAll().stream().map(
                PlantHistoryMapper.INSTANCE::toPlantHistory
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<PlantHistory> findById(Long id) {
        return Optional.of(historyRepository.findById(id).map(PlantHistoryMapper.INSTANCE::toPlantHistory ).get());
    }

    @Override
    public PlantHistory savePlantHistory(PlantHistory plantHistory) {
        return PlantHistoryMapper.INSTANCE.toPlantHistory(
                historyRepository.save(PlantHistoryMapper.INSTANCE.toPlantHistoryEntity(plantHistory)));

    }
}
