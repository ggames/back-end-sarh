package com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

public interface OrganizationalSubUnitSaveServicePort {

    OrganizationalSubUnit saveOrganizationSubUnit(OrganizationalSubUnit suborganizational);
}
