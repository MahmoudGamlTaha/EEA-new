package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.GovernmentDto;
import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.dto.search.GovernmentSearchForm;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Government;
import com.backend.EEA.services.CountryService;
import com.backend.EEA.services.GovernmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/government")
public class GovernmentController extends BaseRestController<Government, GovernmentDto, GovernmentSearchForm> {

    private GovernmentService governmentService;

    public GovernmentController(GovernmentService governmentService) {
        super(governmentService);
        this.governmentService = governmentService;
    }


}
