package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name = "user_role")
@Entity
@Data
public class UserRole extends BaseHeaderEntityGen {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",insertable = false, updatable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" ,insertable = false, updatable = false)
    @JsonIgnore
    private User userAssigned;

    @OneToMany
    Set<Permission> permissions;
}
