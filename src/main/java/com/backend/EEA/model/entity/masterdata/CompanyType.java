package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company_type")
public class CompanyType extends BaseHeaderEntityGen {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}