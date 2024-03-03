package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.dto.masterdata.RdfDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.model.entity.masterdata.Rdf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RdfMapper {
    @Mapping(source = "userId", target = "changerId")
    Rdf toRDF(RdfDto rdfDto);

   @Mapping(source = "changerId", target = "userId")
    RdfDto toRDFDto(Rdf rdf);
}
