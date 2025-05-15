package com.fich.sarh.movement.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.movement.application.ports.persistence.MovementLoadPort;
import com.fich.sarh.movement.application.ports.persistence.MovementRetrievePort;
import com.fich.sarh.movement.application.ports.persistence.MovementSavePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper.MovementMapper;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.repository.MovementRepository;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper.PlantOfPositionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class MovementPersistenceAdapter implements MovementLoadPort, MovementRetrievePort,
        MovementSavePort {

    private final MovementRepository movementRepository;

    public MovementPersistenceAdapter(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public Optional<Movement> loadMovement(Long id) {
        return Optional.of(
                movementRepository.findById(id).map(MovementMapper.INSTANCE::toMovement).get()
        );
    }

    @Override
    public List<Movement> findAllMovements() {
        return MovementMapper.INSTANCE.toMovementList(movementRepository.findAll());
    }

    @Override
    public Optional<Movement> findById(Long id) {
        return Optional.of(
                movementRepository.findById(id).map(MovementMapper.INSTANCE::toMovement).get()
        );
    }

    @Override
    public Movement findByPlant(PlantOfPosition plant) {
        return MovementMapper.INSTANCE.toMovement(movementRepository.fetchMovementByPlant(
                PlantOfPositionMapper.INSTANCE.toPlantOfPositionEntity(plant)));
    }

    @Override
    public List<Movement> fetchMovementWithInactiveAgent(List<PlantStatus> status) {
        return  MovementMapper.INSTANCE.toMovementList(movementRepository.fetchMovementWithInactiveAgent(status));
    }

    @Override
    public Movement saveMovement(Movement movement) {
        return MovementMapper.INSTANCE.toMovement(
                movementRepository.save(MovementMapper.INSTANCE.toMovementEntity(movement))
        );

    }
}
