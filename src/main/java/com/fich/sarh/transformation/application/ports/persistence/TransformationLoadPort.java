package com.fich.sarh.transformation.application.ports.persistence;

import com.fich.sarh.transformation.domain.model.Transformation;

import java.util.Optional;

public interface TransformationLoadPort {

    Optional<Transformation> loadTransformation(Long id);
}
