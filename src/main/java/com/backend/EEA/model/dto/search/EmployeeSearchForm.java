package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSearchForm extends BaseSearchForm {

    private String employeeCode;
    private String employeeName;
}
