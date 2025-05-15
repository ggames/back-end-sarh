package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalSubUnitRetrievePort {

    List<OrganizationalSubUnit> findAllOrganizationalSubUnit();

    Optional<OrganizationalSubUnit> findById(Long id);

    OrganizationalSubUnit findByNameSubUnit(String unit);
}
