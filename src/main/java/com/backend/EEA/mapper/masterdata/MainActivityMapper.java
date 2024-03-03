package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.MainActivityDto;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainActivityMapper {

    RequestHeader toMainActivity(MainActivityDto mainActivityDto);

    MainActivityDto toMainActivityDto(RequestHeader requestHeader);
}
