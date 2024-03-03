package com.backend.EEA.model.entity.masterdata;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
@Data
public class Department extends BaseHeaderEntityGen {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
