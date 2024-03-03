package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.entity.masterdata.RequestType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RequestTypeMapper {
   RequestType toRequestType(RequestTypeDto requestTypeDto);
   RequestTypeDto toRequestTypeDto(RequestType requestType);
}
