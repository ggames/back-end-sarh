package com.fich.sarh.agent.infrastructure.adapter.input.rest.controller;


import com.fich.sarh.agent.application.ports.entrypoint.api.AgentSaveServicePort;
import com.fich.sarh.agent.application.ports.entrypoint.api.AgentUpdateServicePort;
import com.fich.sarh.agent.application.ports.entrypoint.api.AgentRetrieveServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.request.AgentRequest;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.response.AgentResponse;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentRestMapper;

import com.fich.sarh.common.WebAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/agent")
public class AgentController {

    private final AgentSaveServicePort agentSavePort;
    private final AgentRetrieveServicePort agentRetrievePort;
    private final AgentUpdateServicePort agentUpdatePort;
    private final AgentRestMapper restMapper;

    Logger logger = LoggerFactory.getLogger(AgentSavePort.class);
    public AgentController(AgentSaveServicePort agentSavePort, AgentRetrieveServicePort agentRetrievePort, AgentUpdateServicePort agentUpdatePort, AgentRestMapper restMapper) {
        this.agentSavePort = agentSavePort;
        this.agentRetrievePort = agentRetrievePort;
        this.agentUpdatePort = agentUpdatePort;

        this.restMapper = restMapper;
    }

  //  @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<AgentResponse> findAll(){

        return AgentRestMapper.INSTANCE
               .AgentListToAgentResponseList(agentRetrievePort.getAllAgent());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public AgentResponse findByIdAgent(@PathVariable Long id) {
        return AgentRestMapper.INSTANCE.AgentToAgentResponse(agentRetrievePort.findById(id).get());
    }



    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AgentResponse> save(@RequestBody AgentRequest request){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(restMapper.AgentToAgentResponse(
                       agentSavePort.saveAgent(restMapper.AgentRequestToAgent(request))
               ));

    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public AgentResponse update(@PathVariable Long id, @RequestBody AgentRequest request){

        return restMapper.AgentToAgentResponse(agentUpdatePort.updateAgent(id,
                restMapper.AgentRequestToAgent(request) ));
    }
}
