package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CountryRepository;
import com.backend.EEA.business.dao.repositories.masterdat.GovernmentRepository;
import com.backend.EEA.mapper.masterdata.CountryMapper;
import com.backend.EEA.mapper.masterdata.GovernmentMapper;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.GovernmentDto;
import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.dto.search.GovernmentSearchForm;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Government;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GovernmentService extends BaseService<Government, GovernmentDto, GovernmentSearchForm> {
    GovernmentRepository governmentRepository;
    GovernmentMapper governmentMapper;
    public GovernmentService(GovernmentRepository governmentRepository, GovernmentMapper governmentMapper) {
        super(governmentRepository);
        this.governmentRepository = governmentRepository;
        this.governmentMapper = governmentMapper;
    }


    @Override
    protected Specification<Government> buildSpecification(GovernmentSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<GovernmentDto> mapDataListToDtoList(List<Government> list) {
        return list.stream().map(governmentMapper::toGovernmentDto).collect(Collectors.toList());
    }

    @Override
    protected GovernmentDto prepareEntityToDto(Government object) {
        return this.governmentMapper.toGovernmentDto(object);
    }

    @Override
    protected Government prepareDtoToEntity(GovernmentDto object) {
        return this.governmentMapper.toGovernment(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Government object) {
       object.setId(id);
    }

    @Override
    public GovernmentSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery,GovernmentSearchForm.class);
    }
}
