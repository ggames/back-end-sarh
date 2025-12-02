package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.RoleRetrieveServicePort;
import com.fich.sarh.auth.Application.ports.output.persistence.RoleRetrievePort;
import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import com.fich.sarh.common.UseCase;

import java.util.List;
import java.util.Optional;

@UseCase
public class RoleRetrieveUseCase implements RoleRetrieveServicePort {

    private final RoleRetrievePort roleRetrievePort;

    public RoleRetrieveUseCase(RoleRetrievePort roleRetrievePort) {
        this.roleRetrievePort = roleRetrievePort;
    }

    @Override
    public RoleDTO fetchByRoleEnum(RoleEnum name) {
        return roleRetrievePort.fetchByRoleEnum(name);
    }

    @Override
    public List<RoleDTO> fetchAllRole() {
        return roleRetrievePort.fetchAllRole();
    }
}
