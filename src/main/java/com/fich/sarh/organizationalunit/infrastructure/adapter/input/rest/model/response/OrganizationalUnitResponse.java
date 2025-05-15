package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response;

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
public class OrganizationalUnitResponse {

    String nameUnit;

    String director;

    String viceDirector;

    List<OrganizationalSubUnit> subunitList;

}
