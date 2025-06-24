package com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementRetrieveServicePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionRetrieveServicePort;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionSaveServicePort;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionUpdateServicePort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.mapper.PlantOfPositionRestMapper;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.response.PlantOfPositionResponse;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper.PlantOfPositionMapper;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/plant")
public class PlantOfPositionController {

    private final PlantOfPositionRetrieveServicePort retrievePlantService;

    private final PlantOfPositionSaveServicePort savePlantService;

    private final MovementRetrieveServicePort movementRetrieve;
    private final PlantOfPositionUpdateServicePort updatePlantService;

    private final PositionUpdateServicePort positionUpdate;

    private final PositionRetrieveServicePort positionRetrieve;

    Logger logger = LoggerFactory.getLogger(PlantOfPositionController.class);
    public PlantOfPositionController(PlantOfPositionRetrieveServicePort retrievePlantService,
                                     PlantOfPositionSaveServicePort savePlantService,
                                     MovementRetrieveServicePort movementRetrieve, PlantOfPositionUpdateServicePort updatePlantService,
                                     PositionUpdateServicePort positionUpdate, PositionRetrieveServicePort positionRetrieve) {
        this.retrievePlantService = retrievePlantService;
        this.savePlantService = savePlantService;
        this.movementRetrieve = movementRetrieve;
        this.updatePlantService = updatePlantService;
        this.positionUpdate = positionUpdate;
        this.positionRetrieve = positionRetrieve;
    }

    @GetMapping("all")
    public List<PlantOfPositionResponse> findAll(){

        return PlantOfPositionRestMapper.INSTANCE.toPlantOfPositionResponseList(retrievePlantService.getAllPlantOfPositions()) ;

    }

    @PostMapping("create")
    public ResponseEntity<PlantOfPositionResponse> save(@RequestBody PlantOfPositionRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PlantOfPositionRestMapper.INSTANCE.toPlantOfPositionResponse(
                savePlantService.savePlantOfPosition(PlantOfPositionRestMapper.INSTANCE.toPlantOfPosition(request))
        ));


    }

    @PutMapping("update/{id}")
    public PlantOfPositionResponse update(@PathVariable Long id, @RequestBody PlantOfPositionRequest request){



        if(request.getCurrentStatusID()== PlantStatus.FINALIZADO){

            logger.info(request.getCurrentStatusID().toString()  );


            PlantOfPosition plant = retrievePlantService.findById(id).get();

            Movement movement = movementRetrieve.findByPlant(plant);

            if(movement != null){
               Optional<Position> position = positionRetrieve.findById(movement.getPosition().getId());

                logger.info(position.get().getPositionStatus() + " ");
               if(!position.isEmpty()){
                   Position positionUpdated = position.get();
                   positionUpdated.setPositionStatus(StatusOfPositions.SUPRIMIDO);
                   positionUpdate.updatePosition(position.get().getId(), positionUpdated);
               }
           }
        }

        return PlantOfPositionRestMapper.INSTANCE.toPlantOfPositionResponse(updatePlantService.updatePlantOfPosition(id,
                PlantOfPositionRestMapper.INSTANCE.toPlantOfPosition(request)));
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                