package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HarborSearchForm extends BaseSearchForm {
    private String name;

    private String code;
}
