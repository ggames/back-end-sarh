package com.fich.sarh.movement.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {
    @Query("SELECT m FROM MovementEntity m WHERE m.plant = ?1")
    MovementEntity fetchMovementByPlant(PlantOfPositionEntity plant);
    @Query("SELECT m FROM MovementEntity m INNER JOIN m.plant pl ON m.plant.id = pl.id WHERE pl.currentStatusID IN ?1")
   List<MovementEntity> fetchMovementWithInactiveAgent(List<PlantStatus> status);

}
