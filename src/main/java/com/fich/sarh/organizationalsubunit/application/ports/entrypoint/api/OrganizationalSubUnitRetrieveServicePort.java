package com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalSubUnitRetrieveServicePort {

    List<OrganizationalSubUnit> getAllOrganizationalSubUnits();

    Optional<OrganizationalSubUnit> findById(Long id);

    OrganizationalSubUnit findByNameSubUnit(String unit);
}
