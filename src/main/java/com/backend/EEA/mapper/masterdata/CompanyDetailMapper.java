package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyContactDto;
import com.backend.EEA.model.dto.masterdata.CompanyDetailDto;
import com.backend.EEA.model.entity.masterdata.CompanyContact;
import com.backend.EEA.model.entity.masterdata.CompanyDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyDetailMapper {

    CompanyDetail toEntity(CompanyDetail companyDetail);

    List<CompanyDetail> toListOfEntity(List<CompanyDetailDto> companyDetails);
}
