package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnitEntity, Long> {
}
