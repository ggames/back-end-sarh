package com.fich.sarh.organizationalunit.domain.model;

import com.fich.sarh.agent.domain.model.Agent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalUnit {

    Long id;

    String nameUnit;

    Agent director;

    Agent viceDirector;

   // List<OrganizationalSubUnit> subunitList;


    @Override
    public String toString() {
        return "OrganizationalUnit{" +
                "id=" + id +
                ", nameUnit='" + nameUnit + '\'' +
                ", director=" + director +
                ", viceDirector=" + viceDirector +
                '}';
    }
}
