package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitRetrieveServicePort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitSaveServicePort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitUpdateServicePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request.OrganizationalUnitRequest;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response.OrganizationalUnitResponse;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.mapper.OrganizationalUnitRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("hasRole('USER')")
    public List<OrganizationalUnitResponse> findAll(){
        return  OrganizationalUnitRestMapper.INSTANCE.toOrganizationalUnitResponseList(retrieveService.getAllOrganizationalUnits());
    }

    @GetMapping("dto/all")
    @PreAuthorize("hasRole('USER')")
    public List<OrganizationalDTO> findOrganizationalDTO(){
        return retrieveService.findAllOrganizationDto();
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrganizationalUnitResponse> save(@RequestBody OrganizationalUnitRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(OrganizationalUnitRestMapper.INSTANCE
                .toOrganizationalUnitResponse(
                        saveService.saveOrganizationUnit(OrganizationalUnitRestMapper.INSTANCE.toOrganizationalUnit(request))
                ));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public OrganizationalUnit findOrganizationalUnitById(@PathVariable Long id){
        Optional<OrganizationalUnit> organizational = retrieveService.findById(id);
        if(! organizational.isPresent()){
            return null;
        }

        return organizational.get();
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public OrganizationalUnitResponse update(@PathVariable Long id, @RequestBody OrganizationalUnitRequest request){

        return restMapper.toOrganizationalUnitResponse(updateService.updateOrganizationUnit(id,
                restMapper.toOrganizationalUnit(request)
        ));
    }
}
