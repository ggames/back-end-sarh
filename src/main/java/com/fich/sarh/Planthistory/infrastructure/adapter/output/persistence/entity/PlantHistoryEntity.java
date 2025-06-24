package com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Historia_planta")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = PlantOfPositionEntity.class)
    @JoinColumn(name = "planta_cargo_id")
    PlantOfPositionEntity   plantOfPosition;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_planta")
    PlantStatus plantStatus;

    @Column(name = "fecha_desde")
    LocalDate dateFrom;

    @Column(name = "fecha_hasta")
    LocalDate dateTo;
}
