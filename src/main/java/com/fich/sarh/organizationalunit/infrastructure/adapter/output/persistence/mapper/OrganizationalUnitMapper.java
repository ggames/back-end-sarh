package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrganizationalUnitMapper {
    OrganizationalUnitMapper INSTANCE = Mappers.getMapper(OrganizationalUnitMapper.class);
    OrganizationalUnit toDto(OrganizationalUnitEntity entity);
    OrganizationalUnitEntity toEntity(OrganizationalUnit dto);
}