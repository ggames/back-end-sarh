package com.fich.sarh.agent.application.ports.entrypoint.api;



import com.fich.sarh.agent.domain.model.Agent;

import java.util.List;
import java.util.Optional;

public interface AgentRetrieveServicePort {

    List<Agent> getAllAgent();

    Optional<Agent> findById(Long id);
    Agent fetchByDocument(String document);
}
