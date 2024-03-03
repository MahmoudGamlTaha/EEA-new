package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "role")
@Entity
@Getter
@Setter
public class Role extends BaseHeaderEntityGen {
    @Column(name = "name")
    private String name;
    @Column(name = "code", unique = true)
    private String code;

    @OneToMany
    Set<Permission> permissions;
}
