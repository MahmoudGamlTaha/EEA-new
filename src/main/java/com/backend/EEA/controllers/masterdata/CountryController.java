package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.services.CompanyService;
import com.backend.EEA.services.CountryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/country")
public class CountryController extends BaseRestController<Country, CountryDto, CountrySearchForm> {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        super(countryService);
        this.countryService = countryService;
    }


}
