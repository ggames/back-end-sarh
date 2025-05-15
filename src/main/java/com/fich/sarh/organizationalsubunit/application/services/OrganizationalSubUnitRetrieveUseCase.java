package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitRetrieveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrievePort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;

import java.util.List;
import java.util.Optional;

@UseCase
public class OrganizationalSubUnitRetrieveUseCase implements OrganizationalSubUnitRetrieveServicePort {


    private final OrganizationalSubUnitRetrievePort subunitRetrieve;

    public OrganizationalSubUnitRetrieveUseCase(OrganizationalSubUnitRetrievePort subunitRetrieve) {
        this.subunitRetrieve = subunitRetrieve;
    }


    @Override
    public List<OrganizationalSubUnit> getAllOrganizationalSubUnits() {
        return subunitRetrieve.findAllOrganizationalSubUnit();
    }

    @Override
    public Optional<OrganizationalSubUnit> findById(Long id) {
        return  subunitRetrieve.findById(id);
    }

    @Override
    public OrganizationalSubUnit findByNameSubUnit(String subunit) {
        return null;
    }
}
