package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinBranchSearchForm extends BaseSearchForm {
    private String finBranchCode;
    private String finBranchName;
    private String accountNum;
}
