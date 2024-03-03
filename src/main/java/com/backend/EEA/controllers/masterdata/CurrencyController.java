package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.CurrencyDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.CurrencySearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.Currency;
import com.backend.EEA.services.CoalTypeService;
import com.backend.EEA.services.CurrencyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("basic-data/currency")
public class CurrencyController extends BaseRestController<Currency, CurrencyDto, CurrencySearchForm> {
 public CurrencyController(CurrencyService currencyService){
     super(currencyService);
 }
}
