package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.business.dao.repositories.masterdat.CurrencyRepository;
import com.backend.EEA.mapper.masterdata.CoalTypeMapper;
import com.backend.EEA.mapper.masterdata.CurrencyMapper;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.CurrencyDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.CurrencySearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService extends BaseService<Currency, CurrencyDto, CurrencySearchForm> {

    CurrencyRepository currencyRepository;
    CurrencyMapper currencyMapper;
    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        super(currencyRepository);
        this.currencyMapper = currencyMapper;
        this.currencyRepository = currencyRepository;
    }

    @Override
    protected Specification<Currency> buildSpecification(CurrencySearchForm searchParams) {
        return null;
    }

    @Override
    protected List<CurrencyDto> mapDataListToDtoList(List<Currency> list) {
        return list.stream().map(currencyMapper::toCurrencyDto).collect(Collectors.toList());
    }

    @Override
    protected CurrencyDto prepareEntityToDto(Currency object) {
        return currencyMapper.toCurrencyDto(object);
    }

    @Override
    protected Currency prepareDtoToEntity(CurrencyDto object) {
        return currencyMapper.toCurrency(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Currency object) {
          object.setId(id);
    }

    @Override
    public CurrencySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, CurrencySearchForm.class);
    }
}
