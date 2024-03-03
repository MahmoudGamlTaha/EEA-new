package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "name", target = "name")
    Role toRole(RoleDto roleDto);
    @Mapping(source = "name", target = "name")
    RoleDto toRoleDto(Role role);



}
