package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrievePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSavePort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalSubUnitUpdateUseCase implements OrganizationalSubUnitUpdateServicePort {

    private final OrganizationalSubUnitSavePort subunitSavePort;

    private final OrganizationalSubUnitRetrievePort subunitRetrievePort;

    public OrganizationalSubUnitUpdateUseCase(OrganizationalSubUnitSavePort subunitSavePort, OrganizationalSubUnitRetrievePort subunitRetrievePort) {
        this.subunitSavePort = subunitSavePort;
        this.subunitRetrievePort = subunitRetrievePort;
    }


    @Override
    public OrganizationalSubUnit updateOrganizationSubUnit(Long id, OrganizationalSubUnit command) {
        return subunitRetrievePort.findById(id).map(
                subUnit -> {
                    subUnit.setNameSubUnit(command.getNameSubUnit());
                    subUnit.setGuaraniCode(command.getGuaraniCode());
                    subUnit.setOrganizationalUnit(command.getOrganizationalUnit());
                    return subunitSavePort.saveOrganizationalSubUnit(subUnit);
                }
        ).get();
    }
}
