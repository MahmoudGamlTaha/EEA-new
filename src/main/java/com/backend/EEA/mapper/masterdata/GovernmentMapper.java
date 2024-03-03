package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.GovernmentDto;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Government;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GovernmentMapper {

    Government toGovernment(GovernmentDto countryDto);

    GovernmentDto toGovernmentDto(Government government);

}
