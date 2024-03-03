package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    private Long id;
    private String employeeCode;
    private String employeeName;
}
