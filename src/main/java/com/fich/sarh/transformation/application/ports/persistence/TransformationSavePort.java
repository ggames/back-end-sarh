package com.fich.sarh.transformation.application.ports.persistence;

import com.fich.sarh.transformation.domain.model.Transformation;

public interface TransformationSavePort {

    Transformation saveTransformation(Transformation transformation);
}
