package com.fich.sarh.position.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    @Query(value = "SELECT p.id AS id, po.namePosition AS namePosition, o.nameUnit AS nameUnit, p.pointsAvailable AS pointsAvailable," +
            " p.positionStatus AS positionStatus FROM PositionEntity p  LEFT JOIN " +
            " p.organizationalUnitID o LEFT JOIN p.pointID po")
     List<PositionDto> findPositions();

    @Query("SELECT p FROM PositionEntity p WHERE p.positionStatus = ?1")
    List<PositionEntity> findAvailablePosition(StatusOfPositions status);

    List<PositionEntity> findAllByIdIn(List<Long> ids);
}
