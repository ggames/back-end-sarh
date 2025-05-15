package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.Optional;

public interface OrganizationalSubUnitLoadPort {

    Optional<OrganizationalSubUnit> loadOrganizationalSubUnit(Long id);

}
