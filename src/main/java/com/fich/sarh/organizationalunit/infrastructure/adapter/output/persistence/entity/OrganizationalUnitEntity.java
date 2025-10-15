package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "UnidadesOrganizacionales")
public class OrganizationalUnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nombreUnidadOrganizacional")
    String nameUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    AgentEntity director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vicedirector_id", nullable = true)
    AgentEntity viceDirector;

   private boolean isDirectorDifferentFromVice(){
       if(director == null || viceDirector == null){
           return true;
       }
       return !director.getId().equals(viceDirector.getId());
   }
}
