package com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PositionMapper {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    Position toPosition(PositionEntity entity);

    PositionEntity toPositionEntity(Position position);


    List<Position> toPositionList(List<PositionEntity> entityList);

}
