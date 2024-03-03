package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CompanyTypeRepository;
import com.backend.EEA.mapper.masterdata.CompanyTypeMapper;
import com.backend.EEA.model.dto.masterdata.CompanyTypeDto;
import com.backend.EEA.model.dto.search.CompanyTypeSearchForm;
import com.backend.EEA.model.entity.masterdata.CompanyType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyTypeService extends BaseService<CompanyType, CompanyTypeDto, CompanyTypeSearchForm> {

    CompanyTypeRepository companyTypeRepository;
    CompanyTypeMapper companyTypeMapper;
    public CompanyTypeService(CompanyTypeRepository companyTypeRepository, CompanyTypeMapper companyTypeMapper) {
        super(companyTypeRepository);
        this.companyTypeMapper = companyTypeMapper;
        this.companyTypeRepository = companyTypeRepository;
    }

    @Override
    protected Specification<CompanyType> buildSpecification(CompanyTypeSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<CompanyTypeDto> mapDataListToDtoList(List<CompanyType> list) {
        return list.stream().map(companyTypeMapper::toCompanyTypeDto).collect(Collectors.toList());
    }

    @Override
    protected CompanyTypeDto prepareEntityToDto(CompanyType object) {
        return companyTypeMapper.toCompanyTypeDto(object);
    }

    @Override
    protected CompanyType prepareDtoToEntity(CompanyTypeDto object) {
        return companyTypeMapper.toCompanyType(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, CompanyType object) {
          object.setId(id);
    }

    @Override
    public CompanyTypeSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, CompanyTypeSearchForm.class);
    }
}
