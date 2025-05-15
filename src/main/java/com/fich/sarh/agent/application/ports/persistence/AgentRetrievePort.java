package com.fich.sarh.agent.application.ports.persistence;



import com.fich.sarh.agent.domain.model.Agent;

import java.util.List;
import java.util.Optional;

public interface AgentRetrievePort {

    List<Agent> findAll();

    Optional<Agent> findById(Long id);
    Agent findByDocument(String document);
}
