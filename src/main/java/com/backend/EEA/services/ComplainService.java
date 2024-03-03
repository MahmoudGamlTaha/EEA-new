package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.business.dao.repositories.masterdat.ComplainRepository;
import com.backend.EEA.business.dao.specifications.masterdata.ComplainSpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.CoalTypeMapper;
import com.backend.EEA.mapper.masterdata.ComplainMapper;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.dto.masterdata.UserSessionDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.Complains;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplainService extends BaseService<Complains, ComplainsDto, ComplainsSearchForm> {

    ComplainRepository complainRepository;
    ComplainMapper complainMapper;
    public ComplainService(ComplainRepository complainRepository, ComplainMapper complainMapper) {
        super(complainRepository);
        this.complainMapper = complainMapper;
        this.complainRepository = complainRepository;
    }

    @Override
    protected Specification<Complains> buildSpecification(ComplainsSearchForm searchParams) {
        UserSessionDto userSessionDto = getLoggedInUserSession();
        if(userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("customer"))) {
            searchParams.setRequesterId(userSessionDto.getId());
        }
        return ComplainSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<ComplainsDto> mapDataListToDtoList(List<Complains> list) {
        return list.stream().map(complainMapper::toComplainDto).collect(Collectors.toList());
    }

    @Override
    protected ComplainsDto prepareEntityToDto(Complains object) {
        return complainMapper.toComplainDto(object);
    }

    @Override
    protected Complains prepareDtoToEntity(ComplainsDto object) {
        return complainMapper.toComplainEntity(object);
    }
    protected void doBeforeCreate(Complains complains){
        Long currentUserId = getLoggedInUserId();
        if(complains.getRequesterId() != null && complains.getRequesterId().longValue() != currentUserId.longValue() ){
            throw new BusinessException("Not valid user");
        }
        complains.setRequesterId(currentUserId);
    }
    @Override
    protected void setIdBeforeUpdate(long id, Complains object) {
          object.setId(id);
    }

    @Override
    public ComplainsSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, ComplainsSearchForm.class);
    }
}
