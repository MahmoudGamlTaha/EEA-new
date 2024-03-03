package com.backend.EEA.model.entity.operation;

import com.backend.EEA.model.entity.BaseEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "logger")
@Data
public class Logger extends BaseHeaderEntityGen {
    @Column(name = "employee_name")
    private String EmployeeName;
    @Column(name = "employee_manager")
    private String employeeManager;
    @Column(name = "administrative_name")
    private String administrativeName;

    @Column(name = "action")
    private String action;

    @Column(name = "action_order")
    private int order;

    @Column(name = "action_table")
    private String table;

    @Column(name = "row_id")
    private Long rowId;

    @Column(name = "description")
    private String description;
}


