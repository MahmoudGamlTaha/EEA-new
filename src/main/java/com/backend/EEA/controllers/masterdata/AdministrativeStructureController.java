package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.AdministrativeStructureDto;
import com.backend.EEA.model.dto.search.AdministrativeStructureSearchForm;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.services.AdministrativeStructureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/administration")
public class AdministrativeStructureController
        extends BaseRestController<AdministrativeStructure, AdministrativeStructureDto, AdministrativeStructureSearchForm> {

    public AdministrativeStructureController(AdministrativeStructureService administrativeStructureService) {
        super(administrativeStructureService);
    }
}
