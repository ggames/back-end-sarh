package com.fich.sarh.position.application.ports.persistence;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRetrievePort {

    List<Position> findAllPositions();

    Optional<Position> findById(Long id);

    List<Position> findAvailablePosition(StatusOfPositions status);
}
