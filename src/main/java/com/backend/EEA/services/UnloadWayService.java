package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.business.dao.repositories.masterdat.UnloadWayRepository;
import com.backend.EEA.mapper.masterdata.CoalTypeMapper;
import com.backend.EEA.mapper.masterdata.UnloadWayMapper;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.UnloadWayDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.UnloadWaySearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.UnloadWay;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnloadWayService extends BaseService<UnloadWay, UnloadWayDto, UnloadWaySearchForm> {

    UnloadWayRepository unloadWayRepository;
    UnloadWayMapper unloadWayMapper;
    public UnloadWayService(UnloadWayRepository unloadWayRepository, UnloadWayMapper unloadWayMapper) {
        super(unloadWayRepository);
        this.unloadWayMapper = unloadWayMapper;
        this.unloadWayRepository = unloadWayRepository;
    }

    @Override
    protected Specification<UnloadWay> buildSpecification(UnloadWaySearchForm searchParams) {
        return null;
    }

    @Override
    protected List<UnloadWayDto> mapDataListToDtoList(List<UnloadWay> list) {
        return list.stream().map(unloadWayMapper::toUnloadWayDto).collect(Collectors.toList());
    }

    @Override
    protected UnloadWayDto prepareEntityToDto(UnloadWay object) {
        return unloadWayMapper.toUnloadWayDto(object);
    }

    @Override
    protected UnloadWay prepareDtoToEntity(UnloadWayDto object) {
        return unloadWayMapper.toUnloadWay(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, UnloadWay object) {
          object.setId(id);
    }

    @Override
    public UnloadWaySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, UnloadWaySearchForm.class);
    }
}
