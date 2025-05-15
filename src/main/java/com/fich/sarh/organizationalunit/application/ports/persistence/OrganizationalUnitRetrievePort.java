package com.fich.sarh.organizationalunit.application.ports.persistence;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalUnitRetrievePort {

    List<OrganizationalUnit> findAllOrganizationalUnit();

    Optional<OrganizationalUnit> findById(Long id);

    OrganizationalUnit findByNameUnit(String unit);
}
