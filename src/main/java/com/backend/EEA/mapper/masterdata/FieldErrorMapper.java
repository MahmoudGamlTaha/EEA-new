package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.FieldErrorDto;
import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.entity.masterdata.FieldError;
import com.backend.EEA.model.entity.masterdata.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {

    FieldError toEntity(FieldErrorDto fieldErrorDto);

    FieldErrorDto toRoleDto(FieldError fieldError);



}
