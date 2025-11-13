package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import javax.management.relation.Role;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDto(RoleEntity entity);

    RoleEntity toEntity(RoleDTO dto);

    Set<RoleEntity> toEntityList(Set<RoleDTO> roles);

    Set<RoleDTO> toDtoList(Set<RoleEntity> roles);

}
