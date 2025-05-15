package com.fich.sarh.organizationalsubunit.adapter.output.persistence.repository;

import com.fich.sarh.organizationalsubunit.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalSubUnitRepository extends JpaRepository<OrganizationalSubUnitEntity, Long> {
}
