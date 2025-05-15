package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrganizationalUnitMapper {

    OrganizationalUnitMapper INSTANCE = Mappers.getMapper(OrganizationalUnitMapper.class);
    OrganizationalUnitEntity OrganizationalUnitToOrganizationalUnitEntity(OrganizationalUnit organizational);
    OrganizationalUnit OrganizationalUnitEntityToOrganizationalUnit(OrganizationalUnitEntity entity);
    List<OrganizationalUnit> toOrganizationalUnitList(List<OrganizationalUnitEntity> organizationalList);

}
