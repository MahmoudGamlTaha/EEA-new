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
@Table(name = "administrative_structure")
public class AdministrativeStructure extends BaseHeaderEntityGen {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "responsibility")
    private String responsibility;
    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdministrativeStructure parent;
}
