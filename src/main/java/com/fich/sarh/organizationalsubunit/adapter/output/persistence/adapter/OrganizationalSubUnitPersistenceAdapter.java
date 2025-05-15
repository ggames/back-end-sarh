package com.fich.sarh.organizationalsubunit.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.organizationalsubunit.adapter.output.persistence.mapper.OrganizationalSubUnitMapper;
import com.fich.sarh.organizationalsubunit.adapter.output.persistence.repository.OrganizationalSubUnitRepository;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitLoadPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrievePort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSavePort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class OrganizationalSubUnitPersistenceAdapter implements OrganizationalSubUnitLoadPort,
        OrganizationalSubUnitRetrievePort, OrganizationalSubUnitSavePort {

    private final OrganizationalSubUnitRepository subunitRepository;

    Logger logger = LoggerFactory.getLogger(OrganizationalSubUnitPersistenceAdapter.class);
    public OrganizationalSubUnitPersistenceAdapter(OrganizationalSubUnitRepository subunitRepository) {
        this.subunitRepository = subunitRepository;
    }


    @Override
    public Optional<OrganizationalSubUnit> loadOrganizationalSubUnit(Long id) {
        return subunitRepository.findById(id)
                .map(OrganizationalSubUnitMapper.INSTANCE::toOrganizationalSubUnit
                );
    }

    @Override
    public List<OrganizationalSubUnit> findAllOrganizationalSubUnit() {

        return subunitRepository.findAll().stream().map(
                OrganizationalSubUnitMapper.INSTANCE::toOrganizationalSubUnit
        ).collect(Collectors.toList());

    }

    @Override
    public Optional<OrganizationalSubUnit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrganizationalSubUnit findByNameSubUnit(String unit) {
        return null;
    }

    @Override
    public OrganizationalSubUnit saveOrganizationalSubUnit(OrganizationalSubUnit subunit) {
        return OrganizationalSubUnitMapper.INSTANCE.toOrganizationalSubUnit(
                subunitRepository.save(OrganizationalSubUnitMapper.INSTANCE
                        .toOrganizationalSubUnitEntity(subunit))
        );
    }
}
