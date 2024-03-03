package com.backend.EEA.services;

import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.backend.EEA.business.dao.repositories.masterdat.CompanyActivityRepository;
import com.backend.EEA.business.dao.repositories.masterdat.AdministrativeStructureRepository;
import com.backend.EEA.business.dao.specifications.masterdata.ActivitySpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.ActivityMapper;
import com.backend.EEA.model.dto.search.ActivitySearchForm;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyActivityService extends BaseService<CompanyActivity, CompanyActivityDto, ActivitySearchForm> {

    private final ActivityMapper activityMapper;
    private final CompanyActivityRepository companyActivityRepository;

    private final AdministrativeStructureRepository administrativeStructureRepository;


    public CompanyActivityService(CompanyActivityRepository companyActivityRepository,
                                  AdministrativeStructureRepository administrativeStructureRepository,
                                  ActivityMapper activityMapper) {
        super(companyActivityRepository);
        this.activityMapper = activityMapper;
        this.companyActivityRepository = companyActivityRepository;

        this.administrativeStructureRepository = administrativeStructureRepository;
    }

    @Override
    protected Specification<CompanyActivity> buildSpecification(ActivitySearchForm searchParams) {
        return ActivitySpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<CompanyActivityDto> mapDataListToDtoList(List<CompanyActivity> list) {
        return list.stream().map(activityMapper::toActivityDto).collect(Collectors.toList());
    }

    @Override
    protected CompanyActivityDto prepareEntityToDto(CompanyActivity object) {
        return activityMapper.toActivityDto(object);
    }

    @Override
    protected CompanyActivity prepareDtoToEntity(CompanyActivityDto object) {
        return activityMapper.toActivity(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, CompanyActivity object) {
        object.setId(id);
    }

    @Override
    public ActivitySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, ActivitySearchForm.class);
    }

    @Override
    protected void doBeforeCreate(CompanyActivity entity) throws BusinessException {

    }

    @Override
    protected void doBeforeDelete(Long id) throws BusinessException {

    }

    @Override
    protected void doAfterCreate(CompanyActivity entity) throws BusinessException {

    }

    @Override
    protected void doAfterUpdate(CompanyActivity entity) throws BusinessException {

    }

    @Override
    protected void setChangerId(CompanyActivity entity, Long id) {
        super.setChangerId(entity, entity.getChangerId());
    }
}
