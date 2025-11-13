package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.auth.Application.ports.output.persistence.RoleRetrievePort;
import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.RoleMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.RoleRepository;
import com.fich.sarh.common.WebAdapter;

import java.util.Optional;

@WebAdapter
public class RolePersistenceAdapter implements RoleRetrievePort {

    private final RoleRepository roleRepository;

    public RolePersistenceAdapter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO  fetchByRoleEnum(RoleEnum name) {

        return RoleMapper.INSTANCE.toDto(this.roleRepository.findByRoleEnum(name));
    }
}
