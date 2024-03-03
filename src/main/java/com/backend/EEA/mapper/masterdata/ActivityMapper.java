package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(source = "userId", target = "changerId")
    CompanyActivity toActivity(CompanyActivityDto companyActivityDto);

    @Mapping(source = "changerId", target = "userId")
    CompanyActivityDto toActivityDto(CompanyActivity companyActivityDto);
}
