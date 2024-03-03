package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.CurrencyType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Permission")
@Data
public class Permission extends BaseHeaderEntityGen {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE", nullable = false)
    private String code;
}
