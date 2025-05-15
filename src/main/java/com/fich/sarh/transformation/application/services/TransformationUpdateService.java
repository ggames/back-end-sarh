package com.fich.sarh.transformation.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationUpdateServicePort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrievePort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationSavePort;
import com.fich.sarh.transformation.domain.model.Transformation;

@UseCase
public class TransformationUpdateService implements TransformationUpdateServicePort {

    private final TransformationRetrievePort transformationRetrievePort;

    private final TransformationSavePort transformationSavePort;

    public TransformationUpdateService(TransformationRetrievePort transformationRetrievePort, TransformationSavePort transformationSavePort) {
        this.transformationRetrievePort = transformationRetrievePort;
        this.transformationSavePort = transformationSavePort;
    }

    @Override
    public Transformation updateTransformation(Long id, Transformation transformation) {
        return  transformationRetrievePort.findById(id).map(transformedSave ->{
            transformedSave.setResult(transformation.getResult());
            transformedSave.setResolutionNumber(transformation.getResolutionNumber());
            return transformationSavePort.saveTransformation(transformedSave);
        }).get();
    }
}
