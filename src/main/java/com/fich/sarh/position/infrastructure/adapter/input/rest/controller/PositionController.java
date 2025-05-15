package com.fich.sarh.position.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.request.PositionRequest;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.response.PositionResponse;
import com.fich.sarh.position.infrastructure.adapter.input.rest.mapper.PositionRestMapper;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/position")
public class PositionController {

    private final PositionRetrieveServicePort retrieveService;
    private final PositionSaveServicePort saveService;
    private final PositionUpdateServicePort updateService;

    public PositionController(PositionRetrieveServicePort retrieveService, PositionSaveServicePort saveService, PositionUpdateServicePort updateService) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
    }

    @GetMapping("all")
    public List<PositionResponse> findAll() {
        return retrieveService.getAllPositions().stream().map(
                PositionRestMapper.INSTANCE::toPositionResponse
        ).collect(Collectors.toList());
    }

    @PostMapping("create")
    public ResponseEntity<PositionResponse> save(@RequestBody PositionRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PositionRestMapper.INSTANCE.toPositionResponse(
                        saveService.savePosition(PositionRestMapper.INSTANCE.toPosition(request))
                )
        );
    }
}
