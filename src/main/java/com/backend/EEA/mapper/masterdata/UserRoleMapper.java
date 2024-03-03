package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.dto.masterdata.UserRoleDto;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.model.entity.masterdata.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    @Mapping(source = "userId", target = "changerId")
    UserRole toUserRole(UserRoleDto userRoleDto);

    @Mapping(source = "changerId", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    UserRoleDto toUserRoleDto(UserRole userRole);
}
