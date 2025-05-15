package com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;

public interface OrganizationalSubUnitUpdateServicePort {

    OrganizationalSubUnit updateOrganizationSubUnit(Long id, OrganizationalSubUnit command);
}
