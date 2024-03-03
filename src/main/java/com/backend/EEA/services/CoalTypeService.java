package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.business.dao.repositories.masterdat.RequestTypeRepository;
import com.backend.EEA.mapper.masterdata.CoalTypeMapper;
import com.backend.EEA.mapper.masterdata.RequestTypeMapper;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.RequestTypeSearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.RequestType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoalTypeService extends BaseService<CoalType, CoalTypeDto, CoalTypeSearchForm> {

    CoalTypeRepository coalTypeRepository;
    CoalTypeMapper coalTypeMapper;
    public CoalTypeService(CoalTypeRepository coalTypeRepository, CoalTypeMapper coalTypeMapper) {
        super(coalTypeRepository);
        this.coalTypeMapper = coalTypeMapper;
        this.coalTypeRepository = coalTypeRepository;
    }

    @Override
    protected Specification<CoalType> buildSpecification(CoalTypeSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<CoalTypeDto> mapDataListToDtoList(List<CoalType> list) {
        return list.stream().map(coalTypeMapper::toCoalTypeDto).collect(Collectors.toList());
    }

    @Override
    protected CoalTypeDto prepareEntityToDto(CoalType object) {
        return coalTypeMapper.toCoalTypeDto(object);
    }

    @Override
    protected CoalType prepareDtoToEntity(CoalTypeDto object) {
        return coalTypeMapper.toCoalType(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, CoalType object) {
          object.setId(id);
    }

    @Override
    public CoalTypeSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, CoalTypeSearchForm.class);
    }
}
