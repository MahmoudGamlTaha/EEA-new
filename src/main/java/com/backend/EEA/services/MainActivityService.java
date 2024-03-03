package com.backend.EEA.services;

import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.backend.EEA.business.dao.repositories.masterdat.CompanyActivityRepository;
import com.backend.EEA.business.dao.repositories.masterdat.RequestHeaderRepository;
import com.backend.EEA.business.dao.specifications.masterdata.MainActivitySpecifications;
import com.backend.EEA.mapper.masterdata.MainActivityMapper;
import com.backend.EEA.model.dto.masterdata.MainActivityDto;
import com.backend.EEA.model.dto.search.MainActivitySearchForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainActivityService extends BaseService<RequestHeader, MainActivityDto, MainActivitySearchForm> {

    private final MainActivityMapper mainActivityMapper;

    private final RequestHeaderRepository requestHeaderRepository;
    private final CompanyActivityRepository companyActivityRepository;

    public MainActivityService(RequestHeaderRepository requestHeaderRepository,
                               MainActivityMapper mainActivityMapper, CompanyActivityRepository companyActivityRepository) {
        super(requestHeaderRepository);
        this.requestHeaderRepository = requestHeaderRepository;
        this.mainActivityMapper = mainActivityMapper;
        this.companyActivityRepository = companyActivityRepository;
    }

    @Override
    protected Specification<RequestHeader> buildSpecification(MainActivitySearchForm searchParams) {
        return MainActivitySpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<MainActivityDto> mapDataListToDtoList(List<RequestHeader> list) {
        return list.stream().map(mainActivityMapper::toMainActivityDto).collect(Collectors.toList());
    }

    @Override
    protected MainActivityDto prepareEntityToDto(RequestHeader object) {
        return mainActivityMapper.toMainActivityDto(object);
    }

    @Override
    protected RequestHeader prepareDtoToEntity(MainActivityDto object) {
        return mainActivityMapper.toMainActivity(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, RequestHeader object) {
        object.setId(id);
    }

    @Override
    public MainActivitySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, MainActivitySearchForm.class);
    }

    @Override
    protected void doBeforeCreate(RequestHeader entity) throws BusinessException {
        List<RequestHeader> result = requestHeaderRepository.findByEntityIdAndId(getEntityId(), entity.getId());
        if (!result.isEmpty())
            throw new BusinessException("mainActivityAlreadyExist");

        result = requestHeaderRepository.findByNameAndCodeAndEntityId(entity.getName(), entity.getCode(), entity.getEntityId());
        if (!result.isEmpty())
            throw new BusinessException("mainActivityAlreadyExist");
    }

    @Override
    protected void doBeforeEdit(RequestHeader entity) throws BusinessException {
        List<RequestHeader> result = requestHeaderRepository
                .findByEntityIdAndNameAndCodeAndIdNot(getEntityId(), entity.getName(), entity.getCode(), entity.getId());
        if (!result.isEmpty()) throw new BusinessException("mainActivityAlreadyExist");
    }

    @Override
    protected void doBeforeDelete(Long id) throws BusinessException {

    }
}
