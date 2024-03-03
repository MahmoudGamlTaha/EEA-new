package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.DepartmentRepository;
import com.backend.EEA.mapper.masterdata.DepartmentMapper;
import com.backend.EEA.model.dto.masterdata.DepartmentDto;
import com.backend.EEA.model.dto.search.DepartmentSearchForm;
import com.backend.EEA.model.entity.masterdata.Department;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends BaseService<Department, DepartmentDto, DepartmentSearchForm> {

    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper){
     super(departmentRepository);
     this.departmentRepository = departmentRepository;
     this.departmentMapper = departmentMapper;
    }
    @Override
    protected Specification<Department> buildSpecification(DepartmentSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<DepartmentDto> mapDataListToDtoList(List<Department> list) {
        return list.stream().map(departmentMapper::toDepartmentDto).collect(Collectors.toList());
    }

    @Override
    protected DepartmentDto prepareEntityToDto(Department object) {
        return departmentMapper.toDepartmentDto(object);
    }

    @Override
    protected Department prepareDtoToEntity(DepartmentDto object) {
        return departmentMapper.toDepartment(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Department object) {
        object.setId(id);
    }

    @Override
    public DepartmentSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
         return objectMapper.readValue(searchQuery, DepartmentSearchForm.class);
    }
}
