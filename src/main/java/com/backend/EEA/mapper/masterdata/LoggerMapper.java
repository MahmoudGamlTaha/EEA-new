package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.LoggerDto;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.operation.Logger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoggerMapper {

   Logger toLogger(LoggerDto loggerDto);
   LoggerDto toLoggerDto(Logger logger);
}
