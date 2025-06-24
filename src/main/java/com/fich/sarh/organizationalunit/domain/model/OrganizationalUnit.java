package com.fich.sarh.organizationalunit.domain.model;

import com.fich.sarh.organizationalsubunit.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalUnit {

    Long id;

    String nameUnit;

    String director;

    String viceDirector;

   // List<OrganizationalSubUnit> subunitList;


    @Override
    public String toString() {
        return "OrganizationalUnit{" +
                "id=" + id +
                ", nameUnit='" + nameUnit + '\'' +
                ", director='" + director + '\'' +
                ", viceDirector='" + viceDirector + '\'' +
                '}';
    }
}
