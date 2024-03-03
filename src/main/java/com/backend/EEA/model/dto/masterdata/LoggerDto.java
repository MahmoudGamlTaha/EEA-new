package com.backend.EEA.model.dto.masterdata;

import lombok.Data;

import javax.persistence.Column;
@Data
public class LoggerDto {
    private String EmployeeName;

    private String administrativeName;

    private String action;

    private int order;

    private String table;

    private String rowId;
}
