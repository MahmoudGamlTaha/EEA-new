package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Permission;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long userId;

    private Long roleId;

    private String roleName;

    private User userAssigned;

    private Role role;

    Set<Permission> permissions;

}
