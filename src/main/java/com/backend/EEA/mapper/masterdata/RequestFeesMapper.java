package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.RequestFeesDto;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CurrencyRateMapper.class})
public interface RequestFeesMapper {

    RequestFees toEntity(RequestFeesDto requestFeesDto);

    RequestFeesDto toDto(RequestFees requestFees);

}
