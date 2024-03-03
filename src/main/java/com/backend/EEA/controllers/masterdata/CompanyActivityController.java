package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.dto.search.ActivitySearchForm;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.services.CompanyActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/company-activity")
public class CompanyActivityController extends BaseRestController<CompanyActivity, CompanyActivityDto, ActivitySearchForm> {

    public CompanyActivityController(CompanyActivityService companyActivityService) {
        super(companyActivityService);
    }
}
