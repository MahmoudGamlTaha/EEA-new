package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.RequestTypeDto;
import com.backend.EEA.model.dto.search.RequestTypeSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestType;
import com.backend.EEA.services.RequestTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-data/request-type")
public class RequestTypeController extends BaseRestController<RequestType, RequestTypeDto, RequestTypeSearchForm> {

    private RequestTypeService requestTypeService;

    public RequestTypeController(RequestTypeService requestTypeService) {
        super(requestTypeService);
        this.requestTypeService = requestTypeService;
    }


}
