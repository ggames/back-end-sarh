package com.fich.sarh.position.application.ports.entrypoint.api;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRetrieveServicePort {

    List<Position> getAllPositions();

    Optional<Position> findById(Long id);

    List<Position> findAvailablePosition(StatusOfPositions status);
}
