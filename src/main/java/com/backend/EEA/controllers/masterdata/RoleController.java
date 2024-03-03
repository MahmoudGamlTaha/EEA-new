package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.dto.search.RoleSearchForm;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.UserRole;
import com.backend.EEA.services.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/role")
public class RoleController extends BaseRestController<Role, RoleDto, RoleSearchForm> {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }


}
