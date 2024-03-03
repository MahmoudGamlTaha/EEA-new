package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.entity.masterdata.Country;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toCountry(CountryDto countryDto);

    CountryDto toCountryDto(Country country);

}
