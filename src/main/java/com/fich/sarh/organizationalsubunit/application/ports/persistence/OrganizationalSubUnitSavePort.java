package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

public interface OrganizationalSubUnitSavePort {

    OrganizationalSubUnit saveOrganizationalSubUnit(OrganizationalSubUnit unit);
}
