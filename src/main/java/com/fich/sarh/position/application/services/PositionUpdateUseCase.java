package com.fich.sarh.position.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@UseCase
public class PositionUpdateUseCase implements PositionUpdateServicePort {

    Logger logger = LoggerFactory.getLogger(PositionSaveUseCase.class);
    private final PositionRetrievePort positionRetrievePort;

    private final PositionSavePort positionSavePort;

    public PositionUpdateUseCase(PositionRetrievePort positionRetrievePort, PositionSavePort positionSavePort) {
        this.positionRetrievePort = positionRetrievePort;
        this.positionSavePort = positionSavePort;
    }

    @Override
    public Position updatePosition(Long id, Position command) {

        Optional<Position> optionalPosition = positionRetrievePort.findById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        Position position = optionalPosition.get();

        position.setPositionStatus(command.getPositionStatus());
        position.setPointID(command.getPointID());
        position.setOrganizationalUnitID(command.getOrganizationalUnitID());
        position.setNewPosition(command.getNewPosition());
        position.setPointsAvailable(command.getPointsAvailable());
        position.setCreationResolutionID(command.getCreationResolutionID());
        position.setResolutionSuppressionID(command.getResolutionSuppressionID());

        return positionSavePort.savePosition(position);

    }

    @Override
    public Position updatePositionByAvailablePoint(Long id, Position command) {

        Optional<Position> optionalPosition = positionRetrievePort.findById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        Position position = optionalPosition.get();

        position.setPointsAvailable(command.getPointsAvailable());
        position.setNewPosition(command.getNewPosition());
        position.setResolutionSuppressionID(command.getResolutionSuppressionID());
        return positionSavePort.savePosition(position);
    }

    @Override
    public Position updatePositionByOriginator(Long id, Position command) {

        Optional<Position> optionalPosition = positionRetrievePort.findById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        Position position = optionalPosition.get();

        position.setNewPosition(command.getNewPosition());

        return positionSavePort.savePosition(position);
    }
}
