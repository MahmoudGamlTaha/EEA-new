package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.model.entity.masterdata.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ActivityMapper.class, CompanyTypeMapper.class, GovernmentMapper.class})
public interface CompanyMapper {
    @Mapping(source = "commercialNumber", target = "code")
    @Mapping(source = "activityId" , target = "activity_id")
    @Mapping(source = "userId", target = "changerId")
    Company toCompany(CompanyDto dto);
    @Mapping(source = "code", target = "commercialNumber")
    @Mapping(source = "activity_id", target = "activityId")
    @Mapping(source = "changerId", target = "userId")
    CompanyDto toCompanyDto(Company entity);
}
