package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.UnloadWayDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.UnloadWaySearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.UnloadWay;
import com.backend.EEA.services.CoalTypeService;
import com.backend.EEA.services.UnloadWayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("basic-data/unload-way")
public class UnloadWayController extends BaseRestController<UnloadWay, UnloadWayDto, UnloadWaySearchForm> {
 public UnloadWayController(UnloadWayService unloadWayService){
     super(unloadWayService);
 }
}
