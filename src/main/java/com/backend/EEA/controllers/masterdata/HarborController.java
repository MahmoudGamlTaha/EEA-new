package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.dto.search.HarborSearchForm;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.backend.EEA.services.HarborService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/harbor")
public class HarborController extends BaseRestController<Harbor, HarborDto, HarborSearchForm> {

    public HarborController(HarborService harborService) {
        super(harborService);
    }

}
