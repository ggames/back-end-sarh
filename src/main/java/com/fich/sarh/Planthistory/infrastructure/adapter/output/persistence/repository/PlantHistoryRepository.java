package com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.Planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantHistoryRepository extends JpaRepository<PlantHistoryEntity, Long> {
}
