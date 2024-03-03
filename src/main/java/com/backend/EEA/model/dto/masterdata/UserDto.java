package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "username field can't be blank")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "name field can't be blank")
    private String name;

    private Boolean active;

    private List<CompanyDto> company ;

    private DepartmentDto department;

    private Long administrativeId;

    private String password;
    @JsonIgnoreProperties(value = {"userAssigned", "role"})
    private List<UserRoleDto> userRoles;

 }
