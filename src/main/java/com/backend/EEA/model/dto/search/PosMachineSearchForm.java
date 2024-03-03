package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosMachineSearchForm extends BaseSearchForm {
    private String posSerial;
    private String branchName;
    private String posMachineStatusName;
    private String employeeName;
}
