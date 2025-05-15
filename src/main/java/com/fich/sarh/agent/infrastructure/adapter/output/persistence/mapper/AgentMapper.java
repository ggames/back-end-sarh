package  com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper;


import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgentMapper {

  AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

  @Mapping(target = "documenttype", source = "documenttype")
  Agent  AgentEntityToAgent(AgentEntity entity);

  @Mapping(target = "documenttype", source = "documenttype")
  AgentEntity AgentToAgentEntity(Agent agent);
  List<Agent> toAgenList(List<AgentEntity> AgentList);
}
