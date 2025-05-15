package com.fich.sarh.agent.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    @Transactional(readOnly = true)
    Optional<AgentEntity> findByDocument(String document);
}
