package com.fich.sarh.position.application.ports.persistence;

import com.fich.sarh.position.domain.model.Position;

public interface PositionSavePort {

    Position savePosition(Position position);
}
