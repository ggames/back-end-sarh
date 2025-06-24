package com.fich.sarh.position.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrievePort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrievePort;
import com.fich.sarh.transformation.domain.model.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UseCase
public class PositionSaveUseCase implements PositionSaveServicePort {

    Logger logger = LoggerFactory.getLogger(PositionSaveUseCase.class);

    private final PointRetrievePort pointRetrievePort;
    private final PositionSavePort positionSavePort;

    private final PositionRetrievePort positionRetrievePort;
    private final OrganizationalUnitRetrievePort organizationalUnitRetrievePort;
    private final TransformationRetrievePort transformationRetrievePort;
    private final PlantOfPositionRetrievePort plantRetrieve;

    private final PositionUpdateServicePort updateServicePort;

    public PositionSaveUseCase(PointRetrievePort pointRetrievePort, PositionSavePort positionSavePort, PositionRetrievePort positionRetrievePort, OrganizationalUnitRetrievePort organizationalUnitRetrievePort, TransformationRetrievePort transformationRetrievePort, PlantOfPositionRetrievePort plantRetrieve, PositionUpdateServicePort updateServicePort) {
        this.pointRetrievePort = pointRetrievePort;
        this.positionSavePort = positionSavePort;
        this.positionRetrievePort = positionRetrievePort;
        this.organizationalUnitRetrievePort = organizationalUnitRetrievePort;
        this.transformationRetrievePort = transformationRetrievePort;
        this.plantRetrieve = plantRetrieve;
        this.updateServicePort = updateServicePort;
    }

    @Override
    public Position savePosition(PositionCommand command) {


        Optional<Point> pointFound = pointRetrievePort.findById(command.getPointId());


        if(!pointFound.isPresent()){
            throw new RuntimeException("No encontrado");
        }

        Transformation transformation = transformationRetrievePort.findById(command.getResolutionTransformationId()).get();
        OrganizationalUnit organizationalUnit = organizationalUnitRetrievePort.findById(command.getOrganizationalId()).get();
        List<Position> originPositions = positionRetrievePort.findAllByIdIn(command.getOriginPositionIds());

        logger.info("CARGO CREADO " + pointFound.get() + " Origen Cargo " + originPositions );
        Position position = Position.builder()
                .pointID(pointFound.get())
                .organizationalUnitID(organizationalUnit)
                .positionStatus(command.getPositionStatus())
                .pointsAvailable(pointFound.get().getAmountPoint())
                .newPosition(null)
                .creationResolutionID(transformation)
//                .originPosition(originPositions)
                .build();

        logger.info("CARGO CREADO " + position);
        if (!originPositions.isEmpty()) {
            List<Position> positionsCalculate = calculatePosition(originPositions, pointFound.get().getAmountPoint());
            for (Position originator : positionsCalculate) {
                originator.setResolutionSuppressionID(transformation);
                updateServicePort.updatePositionByAvailablePoint(originator.getId(), originator);
            }
            Position positionToUpdate = positionSavePort.savePosition(position);
            logger.info("CARGO COMPLETO " + positionToUpdate);
            for (Position originator : positionsCalculate) {
                originator.setNewPosition(positionToUpdate);
                updateServicePort.updatePositionByOriginator(originator.getId(), originator);
            }

            return positionToUpdate;
        }

       Position positionToUpdate =  positionSavePort.savePosition(position);



        return positionToUpdate;

    }

    public List<Position> calculatePosition(List<Position> positions, Long amountPoint) {

        for (Position originator : positions) {

            long remainder = amountPoint - originator.getPointsAvailable();
            amountPoint = remainder;
            long newAvailable = Math.min(0, remainder);

            originator.setPointsAvailable(newAvailable);

            if (positions.indexOf(originator) == (positions.size() - 1)) {
                newAvailable = remainder >= 0 ? Math.min(0, -remainder) : -remainder;
                originator.setPointsAvailable(newAvailable);
            }

        }
        return positions;


    }


    /*public Position calculatePosition(Position position) {

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

    }*/
}