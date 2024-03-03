package com.backend.EEA.model.dto.search;

import lombok.Data;

@Data
public class CurrencySearchDto extends BaseSearchForm {
    private String currencyName;
    private String currencyCode;
}
