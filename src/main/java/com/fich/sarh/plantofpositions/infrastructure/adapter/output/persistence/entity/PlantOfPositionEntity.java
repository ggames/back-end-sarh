package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "planta_cargos")
public class PlantOfPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_id")
    PointEntity pointID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_id")
    AgentEntity agentID;

    @Enumerated(EnumType.ORDINAL)
    @Column(name ="caracter")
    CharacterPlant characterplantID;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_vigente_id")
    PlantStatus currentStatusID;

    @OneToMany(targetEntity = PlantHistoryEntity.class ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "plantOfPosition")
    List<PlantHistoryEntity> plantHistoryEntities;

}
