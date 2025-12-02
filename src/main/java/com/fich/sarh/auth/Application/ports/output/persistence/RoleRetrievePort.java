package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;

import java.util.List;
import java.util.Optional;

public interface RoleRetrievePort {

    RoleDTO fetchByRoleEnum(RoleEnum name);

    List<RoleDTO> fetchAllRole();

}
