package com.fich.sarh.organizationalsubunit.adapter.input.rest.model.request;

import com.fich.sarh.organizationalsubunit.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalSubUnitRequest {

    String nameSubUnit;

    String guaraniCode;

    OrganizationalUnit organizationalUnit;
}
