package com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper;

import ch.qos.logback.core.read.ListAppender;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    Movement toMovement(MovementEntity entity);

    MovementEntity toMovementEntity(Movement movement);


    List<Movement> toMovementList(List<MovementEntity> entityList);

}
