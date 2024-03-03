package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyTypeDto;
import com.backend.EEA.model.entity.masterdata.CompanyType;
import com.backend.EEA.model.entity.masterdata.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyTypeMapper {
    @Mapping(source = "name", target = "name")
    CompanyType toCompanyType(CompanyTypeDto companyType);

    @Mapping(source = "name", target = "name")
    CompanyTypeDto toCompanyTypeDto(CompanyType companyType);



}
