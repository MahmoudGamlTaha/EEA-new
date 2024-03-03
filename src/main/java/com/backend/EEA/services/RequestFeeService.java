package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.HarborRepository;
import com.backend.EEA.business.dao.repositories.masterdat.RequestFeesRepository;
import com.backend.EEA.business.dao.specifications.masterdata.HarborSpecifications;
import com.backend.EEA.business.dao.specifications.masterdata.RequestFeeSpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.HarborMapper;
import com.backend.EEA.mapper.masterdata.RequestFeesMapper;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.dto.masterdata.RequestFeesDto;
import com.backend.EEA.model.dto.search.HarborSearchForm;
import com.backend.EEA.model.dto.search.RequestFeeSearchForm;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestFeeService extends BaseService<RequestFees, RequestFeesDto, RequestFeeSearchForm> {

    RequestFeesRepository requestFeesRepository;
    RequestFeesMapper requestFeesMapper;
    public RequestFeeService(RequestFeesRepository harborRepository, RequestFeesMapper requestFeesMapper) {
        super(harborRepository);
        this.requestFeesRepository = harborRepository;
        this.requestFeesMapper = requestFeesMapper;
    }

    @Override
    protected Specification<RequestFees> buildSpecification(RequestFeeSearchForm searchParams) {
        return  RequestFeeSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<RequestFeesDto> mapDataListToDtoList(List<RequestFees> list) {
        return list.stream().map(requestFeesMapper::toDto).collect(Collectors.toList());
    }

    @Override
    protected RequestFeesDto prepareEntityToDto(RequestFees object) {
        return requestFeesMapper.toDto(object);
    }

    @Override
    protected RequestFees prepareDtoToEntity(RequestFeesDto object) {
        return requestFeesMapper.toEntity(object) ;
    }

    @Override
    protected void doBeforeCreate(RequestFees entity) throws BusinessException {
        if(entity.getCurrencyRate() != null)
           entity.getCurrencyRate().setEntityId(getEntityId());
        if(this.findFeesByRequestId(entity.getRequestId()) != null){
            throw new BusinessException("fees already submitted");
        }
    }

    @Override
    protected void setIdBeforeUpdate(long id, RequestFees object) {
       object.setId(id);
    }

    @Override
    public RequestFeeSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, RequestFeeSearchForm.class);
    }
    public RequestFees findFeesByRequestId(Long requestId){
       return this.requestFeesRepository.findByRequestId(requestId).orElse(null);
    }
    protected void doBeforeEdit(RequestFees entity) throws BusinessException {

    }
}
