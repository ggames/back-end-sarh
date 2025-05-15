package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.organizationalsubunit.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Builder
@Getter
@Setter
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

    String director;

    String viceDirector;

    //@JsonBackReference
    //@OneToMany(mappedBy = "organizationalUnit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //List<OrganizationalSubUnitEntity> subunitList;
}
