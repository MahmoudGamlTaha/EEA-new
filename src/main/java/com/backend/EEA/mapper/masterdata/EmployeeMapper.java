package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.EmployeeDto;
import com.backend.EEA.model.dto.search.EmployeeSearchForm;
import com.backend.EEA.model.entity.masterdata.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "employeeCode", target = "code")
    @Mapping(source = "employeeName", target = "name")
    Employee toEmployee(EmployeeDto employeeDto);

    @Mapping(source = "code", target = "employeeCode")
    @Mapping(source = "name", target = "employeeName")
    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(source = "code", target = "employeeCode")
    @Mapping(source = "name", target = "employeeName")
    EmployeeSearchForm toEmployeeSearchForm(Employee employee);

}
