package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyContactDto;
import com.backend.EEA.model.entity.masterdata.CompanyContact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyContactMapper{

    CompanyContact toEntity(CompanyContactDto companyContactDto);

    List<CompanyContact> toListOfEntity(List<CompanyContactDto> companyContactDtos);
}
