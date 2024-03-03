package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CompanyTypeDto;
import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.dto.search.CompanyTypeSearchForm;
import com.backend.EEA.model.dto.search.RoleSearchForm;
import com.backend.EEA.model.entity.masterdata.CompanyType;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.services.CompanyTypeService;
import com.backend.EEA.services.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/company-type")
public class CompanyTypeController extends BaseRestController<CompanyType, CompanyTypeDto, CompanyTypeSearchForm> {

    private CompanyTypeService companyTypeService;

    public CompanyTypeController(CompanyTypeService companyTypeService) {
        super(companyTypeService);
        this.companyTypeService = companyTypeService;
    }


}
