package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountrySearchForm extends BaseSearchForm {
    private Long id;
    private String code;
    private String name;
}
