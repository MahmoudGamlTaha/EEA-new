package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.dto.masterdata.UnloadWayDto;
import com.backend.EEA.model.entity.masterdata.RequestType;
import com.backend.EEA.model.entity.masterdata.UnloadWay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnloadWayMapper {
   UnloadWay toUnloadWay(UnloadWayDto requestTypeDto);
   UnloadWayDto toUnloadWayDto(UnloadWay unloadWay);
}
