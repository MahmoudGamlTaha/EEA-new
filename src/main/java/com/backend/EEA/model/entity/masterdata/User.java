package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.BaseHeaderEntityIntegrate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseHeaderEntityGen {

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", length = 3)
    private String password;

    @ManyToOne(optional = true)
    @JoinColumn(name = "administrative_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AdministrativeStructure administrativeStructure;

    @Column(name = "administrative_id")
    private Long administrativeId;

    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Department department;

    @OneToMany(mappedBy = "userAssigned" ,fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<UserRole> userRoles;
    @NotNull
    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "owner")
    private List<Company> company;
}
