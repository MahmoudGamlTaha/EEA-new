package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.BaseHeaderEntityIntegrate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company_activity")
public class CompanyActivity extends BaseHeaderEntityGen {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne( optional = true)
    @JoinColumn(name = "parent_id")
    private CompanyActivity parent;

}