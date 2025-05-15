package com.fich.sarh.organizationalsubunit.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalsubunit.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalsubunit.adapter.input.rest.model.response.OrganizationalSubUnitResponse;
import com.fich.sarh.organizationalsubunit.adapter.input.rest.mapper.OrganizationalSubUnitRestMapper;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitRetrieveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitSaveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/suborganizational")
public class OrganizationalSubUnitController {

    private final OrganizationalSubUnitRetrieveServicePort retrieveService;

    private final OrganizationalSubUnitSaveServicePort saveService;

    private final OrganizationalSubUnitUpdateServicePort updateService;

    private final OrganizationalSubUnitRestMapper restMapper;

    Logger logger = LoggerFactory.getLogger(OrganizationalSubUnitController.class);

    public OrganizationalSubUnitController(OrganizationalSubUnitRetrieveServicePort retrieveService, OrganizationalSubUnitSaveServicePort saveService, OrganizationalSubUnitUpdateServicePort updateService, OrganizationalSubUnitRestMapper restMapper) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
        this.restMapper = restMapper;
    }


    @GetMapping("all")
    public List<OrganizationalSubUnitResponse> findAll(){


        return  retrieveService.getAllOrganizationalSubUnits().stream().map(
                restMapper::toOrganizationalSubUnit
        ).collect(Collectors.toList());
    }

    @PostMapping("create")
    public ResponseEntity<OrganizationalSubUnitResponse> save(@RequestBody OrganizationalSubUnitRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(OrganizationalSubUnitRestMapper.INSTANCE
                .toOrganizationalSubUnit(
                        saveService.saveOrganizationSubUnit(
                                OrganizationalSubUnitRestMapper.INSTANCE
                                        .toOrganizationalSubUnit(request)
                        )));
    }

    @PutMapping("update/{id}")
    public OrganizationalSubUnitResponse update(@PathVariable Long id, @RequestBody OrganizationalSubUnitRequest request){

        return restMapper.toOrganizationalSubUnit(updateService.updateOrganizationSubUnit(id,
                restMapper.toOrganizationalSubUnit(request)
        ));
    }
}
