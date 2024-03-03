package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.services.CustomerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/customer")
@CrossOrigin(origins = "*")
public class CustomerPortalController extends BaseRestController<User, UserDto, UserSearchForm> {

    public CustomerPortalController(CustomerService customerService) {
        super(customerService);
    }
}
