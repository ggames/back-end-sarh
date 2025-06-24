package com.fich.sarh.movement.domain.model;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.domain.model.Position;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movement {

    Long plantId;

    Long positionId;

    Date movementDate;

    String reasonForMovement;

    PlantOfPosition plant;

    Position position;
}
