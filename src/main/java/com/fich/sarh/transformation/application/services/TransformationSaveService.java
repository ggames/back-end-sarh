package com.fich.sarh.transformation.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationSaveServicePort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationSavePort;
import com.fich.sarh.transformation.domain.model.Transformation;

@UseCase
public class TransformationSaveService implements TransformationSaveServicePort {

    private final TransformationSavePort transformationSavePort;

    public TransformationSaveService(TransformationSavePort transformationSavePort) {
        this.transformationSavePort = transformationSavePort;
    }

    @Override
    public Transformation saveTransformation(Transformation transformation) {

        return transformationSavePort.saveTransformation(transformation);
    }
}
