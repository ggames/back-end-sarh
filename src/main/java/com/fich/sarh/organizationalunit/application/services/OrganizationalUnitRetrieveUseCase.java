package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitRetrieveServicePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

@UseCase
public class OrganizationalUnitRetrieveUseCase implements OrganizationalUnitRetrieveServicePort {

    private final OrganizationalUnitRetrievePort organizationalUnitRetrievePort;

    public OrganizationalUnitRetrieveUseCase(OrganizationalUnitRetrievePort organizationalUnitRetrievePort) {
        this.organizationalUnitRetrievePort = organizationalUnitRetrievePort;
    }

    @Override
    public List<OrganizationalUnit> getAllOrganizationalUnits() {
        return organizationalUnitRetrievePort.findAllOrganizationalUnit();
    }

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return organizationalUnitRetrievePort.findById(id);
    }

    @Override
    public OrganizationalUnit findByNameUnit(String unit) {
        return null;
    }
}
