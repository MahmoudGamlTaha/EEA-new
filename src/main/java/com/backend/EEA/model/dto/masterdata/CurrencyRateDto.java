package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Currency;
import com.backend.EEA.model.entity.masterdata.CurrencyRate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyRateDto {
    Double rate;

    Currency currency;

    Long currencyId;

    Date date;
}
