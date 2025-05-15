package com.fich.sarh.agent.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.agent.application.ports.persistence.AgentLoadPort;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentMapper;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.repository.AgentRepository;
import com.fich.sarh.common.PersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class AgentPersistenceAdapter implements AgentLoadPort, AgentSavePort, AgentRetrievePort {

    private final AgentRepository agentRepository;

    public AgentPersistenceAdapter(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    //private final AgentMapper agentMapper;

    Logger logger = LoggerFactory.getLogger(AgentPersistenceAdapter.class);

    @Override
    public Optional<Agent> loadAgent(Long id) {

       // Optional<AgentEntity> agentEntity = agentRepository.findById(id);

       // Agent agentDto = agentEntity.isPresent()? agentMapper.toDto(agentEntity.get()):null;

        return agentRepository.findById(id)
                .map(agent -> AgentMapper.INSTANCE.AgentEntityToAgent(agent));
    }

    @Override
    public Agent saveAgent(Agent agent) {

        return AgentMapper.INSTANCE.AgentEntityToAgent (agentRepository
                .save(AgentMapper.INSTANCE.AgentToAgentEntity(agent)));
        // AgentEntity agentEntity = agentMapper.toEntity(agent);
        // return agentMapper.toDto(agentRepository.save(agentEntity));
    }



    @Override
    public List<Agent> findAll() {

          return  agentRepository.findAll()
                .stream()
                .map(agent -> AgentMapper.INSTANCE.AgentEntityToAgent(agent)).collect(Collectors.toList());
        //return AgentMapper.INSTANCE.toAgenList(agentRepository.findAll());
    }

    @Override
    public Optional<Agent> findById(Long id) {

        return agentRepository.findById(id)
                .map(agent -> AgentMapper.INSTANCE.AgentEntityToAgent(agent));
    }


    @Override
    public Agent findByDocument(String document) {
        Optional<AgentEntity> agentEntity = agentRepository.findByDocument(document);

        Agent agent = AgentMapper.INSTANCE.AgentEntityToAgent(agentEntity.get());
        //return (agent.isPresent())? agentMapper.toDto(agent.get()): null ;
        return agent;
    }
}
