package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderTrackingDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.model.entity.masterdata.RequestHeaderTracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestHeaderTrackingMapper {
    @Mapping(source = "userId", target = "changerId")
    RequestHeaderTracking toEntity(RequestHeaderTrackingDto dto);

    @Mapping(source = "changerId", target = "userId")
    RequestHeaderTrackingDto toDto(RequestHeaderTracking entity);
}
