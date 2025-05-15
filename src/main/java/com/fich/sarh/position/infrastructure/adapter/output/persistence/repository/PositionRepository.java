package com.fich.sarh.position.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {



    @Query("SELECT p FROM PositionEntity p WHERE p.positionStatus = ?1")
    List<PositionEntity> findAvailablePosition(StatusOfPositions status);
}
