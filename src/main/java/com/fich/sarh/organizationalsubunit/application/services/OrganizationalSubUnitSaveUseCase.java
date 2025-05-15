package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitSaveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSavePort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalSubUnitSaveUseCase implements OrganizationalSubUnitSaveServicePort {

    private final OrganizationalSubUnitSavePort subunitSavePort;

    public OrganizationalSubUnitSaveUseCase(OrganizationalSubUnitSavePort subunitSavePort) {
        this.subunitSavePort = subunitSavePort;
    }


    @Override
    public OrganizationalSubUnit saveOrganizationSubUnit(OrganizationalSubUnit organizational) {
        return subunitSavePort.saveOrganizationalSubUnit(organizational);
    }
}
