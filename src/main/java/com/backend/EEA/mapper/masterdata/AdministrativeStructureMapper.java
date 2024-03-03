package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.AdministrativeStructureDto;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdministrativeStructureMapper {
    @Mapping(source = "description", target = "responsibility")
    AdministrativeStructure toAdministrativeStructureEntity(AdministrativeStructureDto dto);
    @Mapping(source = "responsibility", target = "description")
    AdministrativeStructureDto toAdministrativeStructureDto(AdministrativeStructure entity);
}
