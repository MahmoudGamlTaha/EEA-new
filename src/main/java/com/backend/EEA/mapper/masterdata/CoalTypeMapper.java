package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.RequestType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoalTypeMapper {

   CoalType toCoalType(CoalTypeDto coalTypeDto);
   CoalTypeDto toCoalTypeDto(CoalType coalType);
}
