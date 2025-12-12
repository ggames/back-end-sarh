package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrievePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSavePort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.Optional;

@UseCase
public class OrganizationalSubUnitUpdateUseCase implements OrganizationalSubUnitUpdateServicePort {

    private final OrganizationalSubUnitSavePort subunitSavePort;
    private final OrganizationalSubUnitRetrievePort subunitRetrievePort;
    private final OrganizationalUnitRetrievePort organizationalUnitRetrievePort;
    public OrganizationalSubUnitUpdateUseCase(OrganizationalSubUnitSavePort subunitSavePort, OrganizationalSubUnitRetrievePort subunitRetrievePort, OrganizationalUnitRetrievePort organizationalUnitRetrievePort) {
        this.subunitSavePort = subunitSavePort;
        this.subunitRetrievePort = subunitRetrievePort;
        this.organizationalUnitRetrievePort = organizationalUnitRetrievePort;
    }


    @Override
    public OrganizationalSubUnit updateOrganizationSubUnit(Long id, OrganizationalSubUnitRequest command) {

       OrganizationalUnit organizationalUnit = organizationalUnitRetrievePort.findById(command.getOrganizationalUnit())
               .orElseThrow(() -> new BusinessRuleViolationException("No existe el Departamento indicado"));

        return subunitRetrievePort.findById(id).map(
                subUnit -> {
                    subUnit.setNameSubUnit(command.getNameSubUnit());
                    subUnit.setGuaraniCode(command.getGuaraniCode());
                    subUnit.setOrganizationalUnit(organizationalUnit);
                    return subunitSavePort.saveOrganizationalSubUnit(subUnit);
                }
        ).get();
    }
}
