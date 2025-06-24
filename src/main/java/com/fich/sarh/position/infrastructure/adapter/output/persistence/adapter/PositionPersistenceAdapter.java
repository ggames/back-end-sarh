package com.fich.sarh.position.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.application.ports.persistence.PositionLoadPort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionMapper;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.repository.PositionRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class PositionPersistenceAdapter implements PositionRetrievePort, PositionSavePort,
        PositionLoadPort {

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    public PositionPersistenceAdapter(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public Optional<Position> loadPosition(Long id) {
        return  positionRepository.findById(id).map(
                positionMapper::toDto
        );
    }

    @Override
    public List<PositionDto> findAllPositions() {

        return  positionRepository.findPositions();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id).map(
                positionMapper::toDto
        );
    }

    @Override
    public List<Position> findAllByIdIn(List<Long> ids) {

        return positionMapper.toDtoList(positionRepository.findAllByIdIn(ids)) ;
    }

    @Override
    public List<Position> findAvailablePosition(StatusOfPositions status) {
        return positionMapper.toDtoList(positionRepository.findAvailablePosition(status));
    }

    @Override
    @Transactional
    public Position savePosition(Position position) {


        return positionMapper.toDto(
                positionRepository.save(positionMapper.toEntity(position))
        );
    }
}
