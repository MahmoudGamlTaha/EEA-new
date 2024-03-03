package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.BaseService;
import com.backend.EEA.services.RequestHeaderService;
import org.springdoc.webmvc.core.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RequestOperationController extends BaseRestController<RequestHeader, RequestHeaderDto, RequestHeaderSearchForm> {
    private RequestHeaderService requestHeaderService;

    public RequestOperationController(RequestHeaderService requestHeaderService) {
        super(requestHeaderService);
    }

    @RequestMapping(value = "/change-harbor", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> changeHarbor(){
return null;
    }
}
