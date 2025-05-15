package com.fich.sarh.position.application.ports.entrypoint.api;

import com.fich.sarh.position.domain.model.Position;

public interface PositionSaveServicePort {

    Position savePosition(Position position);
}
