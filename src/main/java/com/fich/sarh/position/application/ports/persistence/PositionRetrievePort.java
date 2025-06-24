package com.fich.sarh.position.application.ports.persistence;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;

import java.util.List;
import java.util.Optional;

public interface PositionRetrievePort {

    List<PositionDto> findAllPositions();

    Optional<Position> findById(Long id);

    List<Position> findAllByIdIn(List<Long> ids);

    List<Position> findAvailablePosition(StatusOfPositions status);
}
