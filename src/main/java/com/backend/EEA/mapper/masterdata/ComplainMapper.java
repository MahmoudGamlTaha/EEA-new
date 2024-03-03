package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.entity.masterdata.Complains;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplainMapper {

    Complains toComplainEntity(ComplainsDto dto);

    ComplainsDto toComplainDto(Complains entity);
}
