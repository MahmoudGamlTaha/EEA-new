package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.entity.masterdata.Complains;
import com.backend.EEA.services.ComplainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/complain")
public class ComplainController extends BaseRestController<Complains, ComplainsDto, ComplainsSearchForm> {

    private ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        super(complainService);
        this.complainService = complainService;
    }
}
