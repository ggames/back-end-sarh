package com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "movimiento")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fecha_movimiento")
    Date movementDate;

    @Column(name = "motivo_movimiento")
    String reasonForMovement;

    @ManyToOne(targetEntity = PlantOfPositionEntity.class)
    @JoinColumn(name = "planta_id")
    PlantOfPositionEntity plant;

    @ManyToOne(targetEntity = PositionEntity.class)
    @JoinColumn(name = "cargo_id")
    PositionEntity position;
}
