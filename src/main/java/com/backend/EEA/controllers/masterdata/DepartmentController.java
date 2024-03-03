package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.DepartmentDto;
import com.backend.EEA.model.dto.search.DepartmentSearchForm;
import com.backend.EEA.model.entity.masterdata.Department;
import com.backend.EEA.services.BaseService;
import com.backend.EEA.services.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/department")
public class DepartmentController extends BaseRestController<Department, DepartmentDto, DepartmentSearchForm> {

     private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super(departmentService);
        this.departmentService = departmentService;
    }
}
