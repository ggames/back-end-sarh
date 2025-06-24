package com.fich.sarh.position.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.response.PositionResponse;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionRestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/position")
public class PositionController {

    Logger infoLogger = LoggerFactory.getLogger(PositionController.class);
    private final PositionRetrieveServicePort retrieveService;
    private final PositionSaveServicePort saveService;
    private final PositionUpdateServicePort updateService;

    public PositionController(PositionRetrieveServicePort retrieveService, PositionSaveServicePort saveService, PositionUpdateServicePort updateService) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<PositionDto> findAll() {
        return retrieveService.getAllPositions();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public PositionResponse getPositionById(Long id){
        Optional<Position> positionFound = retrieveService.findById(id);
        if(!positionFound.isPresent()) {
            return null;
        }
        return PositionRestMapper.INSTANCE.toPositionResponse(positionFound.get());
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public Position save(@RequestBody PositionCommand command){
        infoLogger.info("SOLICITUD de CARGO " + command.toString());
        return saveService.savePosition(command);
    }

   /* @PostMapping("create")
    public ResponseEntity<PositionResponse> save(@RequestBody PositionRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PositionRestMapper.INSTANCE.toPositionResponse(
                        saveService.savePosition(PositionRestMapper.INSTANCE.toPosition(request))
                )
        );
    }*/
}
