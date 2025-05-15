package com.fich.sarh.movement.application.ports.entrypoint.api;

import com.fich.sarh.movement.domain.model.Movement;

public interface MovementUpdateServicePort {

    Movement updateMovement(Long id, Movement command);
}
