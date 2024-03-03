package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.CompanyRepository;
import com.backend.EEA.business.dao.repositories.masterdat.CountryRepository;
import com.backend.EEA.mapper.masterdata.CountryMapper;
import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends BaseService<Country, CountryDto, CountrySearchForm> {
    CountryRepository countryRepository;
    CountryMapper countryMapper;
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        super(countryRepository);
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }


    @Override
    protected Specification<Country> buildSpecification(CountrySearchForm searchParams) {
        return null;
    }

    @Override
    protected List<CountryDto> mapDataListToDtoList(List<Country> list) {
        return list.stream().map(countryMapper::toCountryDto).collect(Collectors.toList());
    }

    @Override
    protected CountryDto prepareEntityToDto(Country object) {
        return this.countryMapper.toCountryDto(object);
    }

    @Override
    protected Country prepareDtoToEntity(CountryDto object) {
        return this.countryMapper.toCountry(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Country object) {
       object.setId(id);
    }

    @Override
    public CountrySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery,CountrySearchForm.class);
    }
}
