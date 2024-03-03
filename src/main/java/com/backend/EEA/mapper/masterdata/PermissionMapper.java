package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.PermissionDto;
import com.backend.EEA.model.entity.masterdata.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toDonor(PermissionDto donorDto);
    PermissionDto toDonorDto(Permission permission);

}
