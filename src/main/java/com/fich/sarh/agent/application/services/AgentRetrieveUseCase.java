package com.fich.sarh.agent.application.services;



import com.fich.sarh.agent.application.ports.entrypoint.api.AgentRetrieveServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.UseCase;


import java.util.List;
import java.util.Optional;

@UseCase
public class AgentRetrieveUseCase implements AgentRetrieveServicePort {

    private final AgentRetrievePort agentRetrievePort;

    public AgentRetrieveUseCase(AgentRetrievePort agentRetrievePort) {
        this.agentRetrievePort = agentRetrievePort;
    }

    @Override
    public List<Agent> getAllAgent() {
        return agentRetrievePort.findAll();
    }

    @Override
    public Optional<Agent> findById(Long id) {

        return agentRetrievePort.findById(id);
    }

    @Override
    public Agent fetchByDocument(String document)
    {
        return agentRetrievePort.findByDocument(document);
    }
}
