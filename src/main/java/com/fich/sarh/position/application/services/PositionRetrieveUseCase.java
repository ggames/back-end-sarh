package com.fich.sarh.position.application.services;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;

import java.util.List;
import java.util.Optional;

@UseCase
public class PositionRetrieveUseCase implements PositionRetrieveServicePort {

    private final PositionRetrievePort positionRetrievePort;

    public PositionRetrieveUseCase(PositionRetrievePort positionRetrievePort) {
        this.positionRetrievePort = positionRetrievePort;
    }

    @Override
    public List<PositionDto> getAllPositions() {

        return positionRetrievePort.findAllPositions();
    }

    @Override
    public Optional<Position> findById(Long id) {

        return positionRetrievePort.findById(id);
    }

    @Override
    public List<Position> findAvailablePosition(StatusOfPositions status)
    {
        return positionRetrievePort.findAvailablePosition(StatusOfPositions.SUPRIMIDO);
    }
}
