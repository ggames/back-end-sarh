package com.fich.sarh.organizationalunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

public interface OrganizationalUnitUpdateServicePort {

    OrganizationalUnit updateOrganizationUnit(Long id, OrganizationalUnit command);
}
