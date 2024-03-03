package com.backend.EEA.services;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.business.dao.repositories.masterdat.HarborRepository;
import com.backend.EEA.business.dao.specifications.masterdata.HarborSpecifications;
import com.backend.EEA.mapper.masterdata.HarborMapper;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.dto.search.HarborSearchForm;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarborService extends BaseService<Harbor, HarborDto, HarborSearchForm> {

    HarborRepository harborRepository;
    HarborMapper harborMapper;
    public HarborService(HarborRepository harborRepository, HarborMapper harborMapper) {
        super(harborRepository);
        this.harborRepository = harborRepository;
        this.harborMapper = harborMapper;
    }

    @Override
    protected Specification<Harbor> buildSpecification(HarborSearchForm searchParams) {
        return HarborSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<HarborDto> mapDataListToDtoList(List<Harbor> list) {
        return list.stream().map(harborMapper::toHarborDto).collect(Collectors.toList());
    }

    @Override
    protected HarborDto prepareEntityToDto(Harbor object) {
        return harborMapper.toHarborDto(object);
    }

    @Override
    protected Harbor prepareDtoToEntity(HarborDto object) {
        return harborMapper.toHarbor(object) ;
    }

    @Override
    protected void setIdBeforeUpdate(long id, Harbor object) {
       object.setId(id);
    }

    @Override
    public HarborSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, HarborSearchForm.class);
    }
}
