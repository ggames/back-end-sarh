package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitSaveServicePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSavePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalUnitSaveUseCase implements OrganizationalUnitSaveServicePort {

    private final OrganizationalUnitSavePort organizationalUnitSavePort;

    public OrganizationalUnitSaveUseCase(OrganizationalUnitSavePort organizationalUnitSavePort) {
        this.organizationalUnitSavePort = organizationalUnitSavePort;
    }

    @Override
    public OrganizationalUnit saveOrganizationUnit(OrganizationalUnit organizational) {
        return organizationalUnitSavePort.saveOrganizationalUnit(organizational);
    }
}
