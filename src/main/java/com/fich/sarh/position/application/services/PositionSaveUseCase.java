package com.fich.sarh.position.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrievePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@UseCase
public class PositionSaveUseCase implements PositionSaveServicePort {

    Logger logger = LoggerFactory.getLogger(PositionSaveUseCase.class);
    private final PositionSavePort positionSavePort;

    private final PlantOfPositionRetrievePort plantRetrieve;

    private final PositionUpdateServicePort updateServicePort;

    public PositionSaveUseCase(PositionSavePort positionSavePort, PlantOfPositionRetrievePort plantRetrieve, PositionUpdateServicePort updateServicePort) {
        this.positionSavePort = positionSavePort;
        this.plantRetrieve = plantRetrieve;
        this.updateServicePort = updateServicePort;
    }

    @Override
    public Position savePosition(Position position) {

        Position newPosition = calculatePosition(position);

        List<Position> originators = newPosition.getOriginPosition();

        for (Position originator : originators) {
            updateServicePort.updatePositionByAvailablePoint(originator.getId(), originator);
        }

        Position positionToUpdate = positionSavePort.savePosition(newPosition);

        for (Position originator : originators) {
            originator.setNewPosition(positionToUpdate);
            updateServicePort.updatePositionByOriginator(originator.getId(), originator);
        }

        return positionToUpdate;

    }


    public Position calculatePosition(Position position) {

        Position newPosition;

        Long amountByPoint = position.getPointID().getAmountPoint();

        if (!position.getOriginPosition().isEmpty()) {
            List<Position> originators = position.getOriginPosition();

            List<Position> newListOriginators = new ArrayList<>();

            for (Position originator : originators) {

                Long remainder = amountByPoint - originator.getPointsAvailable();
                amountByPoint = remainder;
                Long newAvailable = Math.min(0, remainder);

                originator.setPointsAvailable(newAvailable);

                if (originators.indexOf(originator) == (originators.size() - 1)) {
                    newAvailable = remainder >= 0? Math.min(0, -remainder):-remainder;
                    originator.setPointsAvailable(newAvailable);
                }

            }
            return position;
        }

        return position;

    }
}