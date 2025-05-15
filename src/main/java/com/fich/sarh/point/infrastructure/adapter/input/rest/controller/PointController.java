package com.fich.sarh.point.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.point.application.ports.entrypoint.api.PointRetrieveServicePort;
import com.fich.sarh.point.application.ports.entrypoint.api.PointSaveServicePort;
import com.fich.sarh.point.application.ports.entrypoint.api.PointUpdateServicePort;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.request.PointRequest;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.response.PointResponse;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper.PointRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/point")
public class PointController {

    private final PointSaveServicePort pointSaveServicePort;

    private final PointRetrieveServicePort pointRetrieveServicePort;

    private final PointUpdateServicePort pointUpdateServicePort;

    public PointController(PointSaveServicePort pointSaveServicePort, PointRetrieveServicePort pointRetrieveServicePort, PointUpdateServicePort pointUpdateServicePort) {
        this.pointSaveServicePort = pointSaveServicePort;
        this.pointRetrieveServicePort = pointRetrieveServicePort;
        this.pointUpdateServicePort = pointUpdateServicePort;
    }

    @GetMapping("all")
    public List<PointResponse> findAll(){
        return pointRetrieveServicePort.getAllPoints().stream().map(
                PointRestMapper.INSTANCE::PointToPointResponse
        ).collect(Collectors.toList());
    }

    @PostMapping("create")
    public ResponseEntity<PointResponse> save(@RequestBody PointRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                 PointRestMapper.INSTANCE.PointToPointResponse(pointSaveServicePort.savePoint(
                         PointRestMapper.INSTANCE.PointRequestToPoint(request)
                 ))
        );
    }


}
