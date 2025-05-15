package com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PointMapper {

    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    Point PointEntityToPoint(PointEntity entity);

    PointEntity PointToPointEntity(Point point);

    List<Point> toPointList(List<Point> entityList);
}
