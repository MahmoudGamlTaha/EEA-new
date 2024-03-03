package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CurrencyDto;
import com.backend.EEA.model.entity.masterdata.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
   Currency toCurrency(CurrencyDto currencyDto);
   CurrencyDto toCurrencyDto(Currency currency);
}
