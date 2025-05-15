package com.fich.sarh.position.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.application.ports.persistence.PositionLoadPort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionMapper;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.repository.PositionRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class PositionPersistenceAdapter implements PositionRetrievePort, PositionSavePort,
        PositionLoadPort {

    private final PositionRepository positionRepository;

    public PositionPersistenceAdapter(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Optional<Position> loadPosition(Long id) {
        return  positionRepository.findById(id).map(
                PositionMapper.INSTANCE::toPosition
        );
    }

    @Override
    public List<Position> findAllPositions() {
        return  positionRepository.findAll().stream().map(
                PositionMapper.INSTANCE::toPosition
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id).map(
                PositionMapper.INSTANCE::toPosition
        );
    }

    @Override
    public List<Position> findAvailablePosition(StatusOfPositions status) {
        return PositionMapper.INSTANCE.toPositionList(positionRepository.findAvailablePosition(status));
    }

    @Override
    @Transactional
    public Position savePosition(Position position) {


        return PositionMapper.INSTANCE.toPosition(
                positionRepository.save(PositionMapper.INSTANCE.toPositionEntity(position))
        );
    }
}
