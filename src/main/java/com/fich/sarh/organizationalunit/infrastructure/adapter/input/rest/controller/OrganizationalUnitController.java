package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitRetrieveServicePort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitSaveServicePort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitUpdateServicePort;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request.OrganizationalUnitRequest;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response.OrganizationalUnitResponse;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.mapper.OrganizationalUnitRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/organizational")
public class OrganizationalUnitController {

    private final OrganizationalUnitRetrieveServicePort retrieveService;

    private final OrganizationalUnitSaveServicePort saveService;

    private final OrganizationalUnitUpdateServicePort updateService;

    private final OrganizationalUnitRestMapper restMapper;
    public OrganizationalUnitController(OrganizationalUnitRetrieveServicePort retrieveService,
                                        OrganizationalUnitSaveServicePort saveService, OrganizationalUnitUpdateServicePort updateService, OrganizationalUnitRestMapper restMapper) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
        this.restMapper = restMapper;
    }


    @GetMapping("all")
    public List<OrganizationalUnitResponse> findAll(){
        return  OrganizationalUnitRestMapper.INSTANCE.toOrganizationalUnitResponseList(retrieveService.getAllOrganizationalUnits());
    }

    @PostMapping("create")
    public ResponseEntity<OrganizationalUnitResponse> save(@RequestBody OrganizationalUnitRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(OrganizationalUnitRestMapper.INSTANCE
                .OrganizationalUnitToOrganizationalUnitResponse(
                        saveService.saveOrganizationUnit(OrganizationalUnitRestMapper.INSTANCE.OrganizationalUnitRequestToOrganizationalUnit(request))
                ));
    }

    @PutMapping("update/{id}")
    public OrganizationalUnitResponse update(@PathVariable Long id, @RequestBody OrganizationalUnitRequest request){

        return restMapper.OrganizationalUnitToOrganizationalUnitResponse(updateService.updateOrganizationUnit(id,
                restMapper.OrganizationalUnitRequestToOrganizationalUnit(request)
        ));
    }
}
