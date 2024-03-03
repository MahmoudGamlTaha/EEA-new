package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.DepartmentDto;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.entity.masterdata.Department;
import com.backend.EEA.model.entity.masterdata.Harbor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentDto DepartmentDto);

    DepartmentDto toDepartmentDto(Department department);

}
