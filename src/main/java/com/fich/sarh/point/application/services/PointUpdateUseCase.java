package com.fich.sarh.point.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.point.application.ports.entrypoint.api.PointUpdateServicePort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.application.ports.persistence.PointSavePort;
import com.fich.sarh.point.domain.model.Point;

@UseCase
public class PointUpdateUseCase implements PointUpdateServicePort {

    private final PointRetrievePort pointRetrievePort;

    private final PointSavePort pointSavePort;

    public PointUpdateUseCase(PointRetrievePort pointRetrievePort, PointSavePort pointSavePort) {
        this.pointRetrievePort = pointRetrievePort;
        this.pointSavePort = pointSavePort;
    }

    @Override
    public Point updatePoint(Long id, Point command) {
        return pointRetrievePort.findById(id).map(
                point -> {
                    point.setAmountPoint(command.getAmountPoint());
                    point.setPositionCode(command.getPositionCode());
                    point.setDedication(command.getDedication());
                    point.setNamePosition(command.getNamePosition());
                    point.setDate(command.getDate());
                    return pointSavePort.savePoint(point);
                }
        ).get() ;
    }
}
