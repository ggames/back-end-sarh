package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request;

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
public class OrganizationalUnitRequest {

    String nameUnit;

    String director;

    String viceDirector;

    List<OrganizationalSubUnit> subunitList;
}
