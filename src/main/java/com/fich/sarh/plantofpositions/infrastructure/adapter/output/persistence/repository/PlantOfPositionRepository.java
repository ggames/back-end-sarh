package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlantOfPositionRepository extends JpaRepository<PlantOfPositionEntity, Long> {


}
