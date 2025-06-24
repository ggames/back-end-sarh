package com.fich.sarh.transformation.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationRetrieveServicePort;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationSaveServicePort;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationUpdateServicePort;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.request.TransformationRequest;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.response.TransformationResponse;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.mapper.TransformationRestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/transformation")
public class TransformationController {

    Logger logger = LoggerFactory.getLogger(TransformationController.
            class);
    private final TransformationRetrieveServicePort transformationRetrieveServicePort;

    private final TransformationUpdateServicePort transformationUpdateServicePort;

    private final TransformationSaveServicePort transformationSaveServicePort;

    public TransformationController(TransformationRetrieveServicePort transformationRetrieveServicePort, TransformationUpdateServicePort transformationUpdateServicePort, TransformationSaveServicePort transformationSaveServicePort) {
        this.transformationRetrieveServicePort = transformationRetrieveServicePort;
        this.transformationUpdateServicePort = transformationUpdateServicePort;
        this.transformationSaveServicePort = transformationSaveServicePort;
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransformationResponse> save(@RequestBody TransformationRequest request) {

        logger.error("VALOR DE LA TRANSFORMACION", request.getResolutionNumber(), request.getResult());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                TransformationRestMapper.INSTANCE.toTransformationResponse(
                        transformationSaveServicePort.saveTransformation(
                                TransformationRestMapper.INSTANCE.toTransformation(request)
                        )
                )
        );
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<TransformationResponse> getAll() {

        return transformationRetrieveServicePort
                .getAllTransformations().stream().map(
                        TransformationRestMapper.INSTANCE::toTransformationResponse
                ).collect(Collectors.toList());
    }

    @GetMapping("last")
    @PreAuthorize("hasRole('USER')")
    public TransformationResponse getTransformationLast() {
        return TransformationRestMapper.INSTANCE.toTransformationResponse(transformationRetrieveServicePort.findFirstByOrderDesc().get());
    }
}
