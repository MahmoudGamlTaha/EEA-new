package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "UnloadWay")
@Entity
@Data
public class UnloadWay extends BaseHeaderEntityGen {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;
}
