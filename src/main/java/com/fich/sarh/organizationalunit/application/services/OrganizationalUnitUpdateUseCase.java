package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitUpdateServicePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSavePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalUnitUpdateUseCase implements OrganizationalUnitUpdateServicePort {

    private final OrganizationalUnitSavePort organizationalUnitSavePort;

    private final OrganizationalUnitRetrievePort organizationalUnitRetrievePort;

    public OrganizationalUnitUpdateUseCase(OrganizationalUnitSavePort organizationalUnitSavePort, OrganizationalUnitRetrievePort organizationalUnitRetrievePort) {
        this.organizationalUnitSavePort = organizationalUnitSavePort;
        this.organizationalUnitRetrievePort = organizationalUnitRetrievePort;
    }

    @Override
    public OrganizationalUnit updateOrganizationUnit(Long id, OrganizationalUnit command) {
        return organizationalUnitRetrievePort.findById(id).map(
                organizationalUnit -> {
                    organizationalUnit.setNameUnit(command.getNameUnit());
                    organizationalUnit.setDirector(command.getDirector());
                    organizationalUnit.setViceDirector(command.getViceDirector());
                    return organizationalUnitSavePort.saveOrganizationalUnit(organizationalUnit);
                }
        ).get();
    }
}
