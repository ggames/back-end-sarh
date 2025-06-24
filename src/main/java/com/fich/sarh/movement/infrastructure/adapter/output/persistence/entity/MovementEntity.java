package com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "movimiento")
@Getter
@Setter
public class MovementEntity implements Serializable {

    @EmbeddedId
    MovementId id;



    @ManyToOne(targetEntity = PlantOfPositionEntity.class)
    @MapsId("plantId")
    PlantOfPositionEntity plant;

    @ManyToOne(targetEntity = PositionEntity.class)
    @MapsId("positionId")
    PositionEntity position;

    @Column(name = "fecha_movimiento")
    Date movementDate;

    @Column(name = "motivo_movimiento")
    String reasonForMovement;
}
