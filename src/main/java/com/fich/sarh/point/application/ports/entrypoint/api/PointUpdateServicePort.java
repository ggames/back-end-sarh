package com.fich.sarh.point.application.ports.entrypoint.api;

import com.fich.sarh.point.domain.model.Point;

public interface PointUpdateServicePort {

    Point updatePoint(Long id, Point command);
}
