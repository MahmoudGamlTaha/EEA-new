package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.RequestTypeRepository;
import com.backend.EEA.mapper.masterdata.RequestTypeMapper;
import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.dto.search.RequestTypeSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestTypeService extends BaseService<RequestType, RequestTypeDto, RequestTypeSearchForm> {

    RequestTypeRepository requestTypeRepository;
    RequestTypeMapper requestTypeMapper;
    public RequestTypeService(RequestTypeRepository requestTypeRepository, RequestTypeMapper requestTypeMapper) {
        super(requestTypeRepository);
        this.requestTypeMapper = requestTypeMapper;
        this.requestTypeRepository = requestTypeRepository;
    }

    @Override
    protected Specification<RequestType> buildSpecification(RequestTypeSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<RequestTypeDto> mapDataListToDtoList(List<RequestType> list) {
        return list.stream().map(requestTypeMapper::toRequestTypeDto).collect(Collectors.toList());
    }

    @Override
    protected RequestTypeDto prepareEntityToDto(RequestType object) {
        return requestTypeMapper.toRequestTypeDto(object);
    }

    @Override
    protected RequestType prepareDtoToEntity(RequestTypeDto object) {
        return requestTypeMapper.toRequestType(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, RequestType object) {
          object.setId(id);
    }

    @Override
    public RequestTypeSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, RequestTypeSearchForm.class);
    }
}
