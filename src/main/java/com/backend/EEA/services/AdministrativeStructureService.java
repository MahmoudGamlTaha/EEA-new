package com.backend.EEA.services;

import com.backend.EEA.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.backend.EEA.business.dao.repositories.masterdat.AdministrativeStructureRepository;
import com.backend.EEA.business.dao.specifications.masterdata.AdministrativeStructureSpecifications;
import com.backend.EEA.mapper.masterdata.AdministrativeStructureMapper;
import com.backend.EEA.model.dto.masterdata.AdministrativeStructureDto;
import com.backend.EEA.model.dto.search.AdministrativeStructureSearchForm;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.model.entity.masterdata.Company;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministrativeStructureService extends BaseService<AdministrativeStructure, AdministrativeStructureDto, AdministrativeStructureSearchForm> {

    private final AdministrativeStructureRepository administrativeStructureRepository;

    private final AdministrativeStructureMapper administrativeStructureMapper;


    public AdministrativeStructureService(AdministrativeStructureRepository administrativeStructureRepository,  AdministrativeStructureMapper administrativeStructureMapper) {
        super(administrativeStructureRepository);
        this.administrativeStructureMapper = administrativeStructureMapper;
        this.administrativeStructureRepository = administrativeStructureRepository;
    }

    @Override
    protected Specification<AdministrativeStructure> buildSpecification(AdministrativeStructureSearchForm searchParams) {
        return AdministrativeStructureSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<AdministrativeStructureDto> mapDataListToDtoList(List<AdministrativeStructure> list) {
        return list.stream().map(administrativeStructureMapper::toAdministrativeStructureDto).collect(Collectors.toList());
    }

    @Override
    protected AdministrativeStructureDto prepareEntityToDto(AdministrativeStructure object) {
        return administrativeStructureMapper.toAdministrativeStructureDto(object);
    }

    @Override
    protected AdministrativeStructure prepareDtoToEntity(AdministrativeStructureDto object) {
        return administrativeStructureMapper.toAdministrativeStructureEntity(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, AdministrativeStructure object) {
        object.setId(id);
    }

    @Override
    public AdministrativeStructureSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, AdministrativeStructureSearchForm.class);
    }

    /* in case add root node, check that the root node already exists */
    @Override
    protected void doBeforeCreate(AdministrativeStructure entity) throws BusinessException {
        // check that the data already exists

    }

    @Override
    protected void doBeforeEdit(AdministrativeStructure entity) throws BusinessException {

    }

    @Override
    protected void doBeforeDelete(Long id) throws BusinessException {

    }

    @Override
    protected void doAfterCreate(AdministrativeStructure entity) throws BusinessException {

    }

    @Override
    protected void doAfterUpdate(AdministrativeStructure entity) throws BusinessException {

    }

    private void validateName(AdministrativeStructure entity) {
    }
}
