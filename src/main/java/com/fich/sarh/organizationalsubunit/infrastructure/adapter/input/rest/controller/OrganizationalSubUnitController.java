package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.response.OrganizationalSubUnitResponse;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.mapper.OrganizationalSubUnitRestMapper;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitRetrieveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitSaveServicePort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateServicePort;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.mapper.OrganizationalSubUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/suborganizational")
public class OrganizationalSubUnitController {

    private final OrganizationalSubUnitRetrieveServicePort retrieveService;

    private final OrganizationalSubUnitSaveServicePort saveService;

    private final OrganizationalSubUnitUpdateServicePort updateService;

    private final OrganizationalSubUnitRestMapper restMapper;

    //private final OrganizationalSubUnitMapper suborganizationalMapper;

    Logger logger = LoggerFactory.getLogger(OrganizationalSubUnitController.class);

    public OrganizationalSubUnitController(OrganizationalSubUnitRetrieveServicePort retrieveService, OrganizationalSubUnitSaveServicePort saveService,
                                           OrganizationalSubUnitUpdateServicePort updateService,
                                           OrganizationalSubUnitRestMapper restMapper) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
        this.restMapper = restMapper;
       // this.suborganizationalMapper = suborganizationalMapper;
    }

@GetMapping("{id}")
@PreAuthorize("hasRole('USER')")
 public OrganizationalSubUnit findOrganizationalSubUnitById(@PathVariable Long id){
        Optional<OrganizationalSubUnit> organizationalSubunit = retrieveService.findById(id);

        if(!organizationalSubunit.isPresent()) return null;

        return organizationalSubunit.get();
 }


    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<OrganizationalSubUnit> findAll(){


        return  retrieveService.getAllOrganizationalSubUnits();
                /*.stream().map(suborganizational ->
                suborganizationalMapper.toOrganizationalSubUnit(suborganizational)
        ).collect(Collectors.toList());*/
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrganizationalSubUnitResponse> save(@RequestBody OrganizationalSubUnitRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(OrganizationalSubUnitRestMapper.INSTANCE
                .toOrganizationalSubUnit(
                        saveService.saveOrganizationSubUnit(
                                OrganizationalSubUnitRestMapper.INSTANCE
                                        .toOrganizationalSubUnit(request)
                        )));
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public OrganizationalSubUnitResponse update(@PathVariable Long id, @RequestBody OrganizationalSubUnitRequest request){

        return restMapper.toOrganizationalSubUnit(updateService.updateOrganizationSubUnit(id,
                restMapper.toOrganizationalSubUnit(request)
        ));
    }

    @GetMapping("dto/all")
    public List<OrganizationalSubUnitDTO> findAllDto(){

        return retrieveService.getAllOrganizationalSubUnitDTOs();
    }
}
