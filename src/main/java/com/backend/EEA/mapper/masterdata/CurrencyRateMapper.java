package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CurrencyRateDto;
import com.backend.EEA.model.dto.masterdata.RequestFeesDto;
import com.backend.EEA.model.entity.masterdata.CurrencyRate;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper {

    CurrencyRate toEntity(CurrencyRateDto currencyRateDto);

    CurrencyRateDto toDto(CurrencyRate currencyRate);

}
