package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitLoadPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrievePort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSavePort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper.OrganizationalUnitMapper;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.repository.OrganizationalUnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class OrganizationalUnitPersistenceAdapter implements OrganizationalUnitLoadPort,
        OrganizationalUnitRetrievePort, OrganizationalUnitSavePort {

    private final OrganizationalUnitRepository organizationalRepository;

    public OrganizationalUnitPersistenceAdapter(OrganizationalUnitRepository organizationalRepository) {
        this.organizationalRepository = organizationalRepository;
    }

    @Override
    public Optional<OrganizationalUnit> loadOrganizationalUnit(Long id) {
        return organizationalRepository.findById(id)
                .map(organizational ->
                        OrganizationalUnitMapper.
                                INSTANCE.OrganizationalUnitEntityToOrganizationalUnit(organizational)
                );
    }

    @Override
    public List<OrganizationalUnit> findAllOrganizationalUnit() {
        return organizationalRepository.findAll().stream()
                .map(organizational ->
                        OrganizationalUnitMapper.INSTANCE.OrganizationalUnitEntityToOrganizationalUnit(organizational))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return organizationalRepository.findById(id).map(
                organizational -> OrganizationalUnitMapper.INSTANCE.OrganizationalUnitEntityToOrganizationalUnit(organizational)
        );
    }

    @Override
    public OrganizationalUnit findByNameUnit(String unit) {
        return null;
    }


    @Override
    public OrganizationalUnit saveOrganizationalUnit(OrganizationalUnit unit) {
        return
                OrganizationalUnitMapper.INSTANCE.OrganizationalUnitEntityToOrganizationalUnit(
                        organizationalRepository.save(OrganizationalUnitMapper.INSTANCE.OrganizationalUnitToOrganizationalUnitEntity(unit))
                );

    }
}
