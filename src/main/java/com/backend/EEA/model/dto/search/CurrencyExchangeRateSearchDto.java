package com.backend.EEA.model.dto.search;

import lombok.Data;

import java.util.Date;

@Data
public class CurrencyExchangeRateSearchDto extends BaseSearchForm {
    private String currencyName;
    private Date fromDate;
    private Date toDate;
}
