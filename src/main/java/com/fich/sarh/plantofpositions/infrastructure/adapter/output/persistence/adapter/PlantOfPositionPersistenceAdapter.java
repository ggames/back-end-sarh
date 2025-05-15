package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionLoadPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrievePort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSavePort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper.PlantOfPositionMapper;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@PersistenceAdapter
public class PlantOfPositionPersistenceAdapter implements PlantOfPositionRetrievePort,
        PlantOfPositionSavePort, PlantOfPositionLoadPort {

    private final PlantOfPositionRepository plantRepository;

    private PlantOfPositionMapper mapper;

    Logger logger = LoggerFactory.getLogger(PlantOfPositionPersistenceAdapter.class);
    public PlantOfPositionPersistenceAdapter(PlantOfPositionRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Optional<PlantOfPosition> loadPlantOfPosition(Long id) {
        return plantRepository.findById(id).map(
                mapper::toPlantOfPosition
        );
    }

    @Override
    public List<PlantOfPosition> findAllPlantOfPosition() {

        logger.error("CANTIDAD DE REGISTROS  " + String.valueOf(plantRepository.findAll().size()) );

        return PlantOfPositionMapper.INSTANCE.toPlantOfPositionList(plantRepository.findAll()) ;
       // return plants.isEmpty() ? List.of() : plants.stream().map(
       //         mapper::toPlantOfPosition
       // ).collect(Collectors.toList()) ;
    }

    @Override
    public Optional<PlantOfPosition> findById(Long id) {
        return Optional.of(PlantOfPositionMapper.INSTANCE.toPlantOfPosition(plantRepository.findById(id).get())) ;
    }


    @Override
    public PlantOfPosition savePlantOfPosition(PlantOfPosition plantposition) {
        return  PlantOfPositionMapper.INSTANCE
                .toPlantOfPosition(plantRepository
                .save(PlantOfPositionMapper.INSTANCE.toPlantOfPositionEntity(plantposition)) ) ;
    }
}
