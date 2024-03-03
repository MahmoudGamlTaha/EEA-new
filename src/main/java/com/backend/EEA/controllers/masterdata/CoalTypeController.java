package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.business.dao.repositories.masterdat.CoalTypeRepository;
import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.services.CoalTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("basic-data/coal-type")
public class CoalTypeController extends BaseRestController<CoalType, CoalTypeDto, CoalTypeSearchForm> {
 public CoalTypeController(CoalTypeService coalTypeService){
     super(coalTypeService);
 }
}
