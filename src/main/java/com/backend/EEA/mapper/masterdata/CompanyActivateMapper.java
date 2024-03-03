package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyActivateDto;
import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivate;
import com.backend.EEA.model.entity.masterdata.Complains;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class})
public interface CompanyActivateMapper {

    CompanyActivate toCompanyActivateEntity(CompanyActivateDto dto);

    CompanyActivateDto toCompanyActivateDto(CompanyActivate entity);
}
