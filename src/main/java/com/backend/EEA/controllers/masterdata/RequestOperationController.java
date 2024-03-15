package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.backend.EEA.model.payload.request.AddCommentsToRequestHeaderRequest;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.BaseService;
import com.backend.EEA.services.RequestHeaderService;
import java.util.List;
import org.springdoc.webmvc.core.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request-operation")
public class RequestOperationController extends BaseRestController<RequestHeader, RequestHeaderDto, RequestHeaderSearchForm> {
    @Autowired
    private RequestHeaderService requestHeaderService;

    public RequestOperationController(RequestHeaderService requestHeaderService) {
        super(requestHeaderService);
    }

    @RequestMapping(value = "/change-harbor", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> changeHarbor(){
return null;
    }

    @RequestMapping(value = "/add-comments-to-request/{requestId}", method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> addCommentsToRequest(@PathVariable Long requestId,@RequestBody AddCommentsToRequestHeaderRequest request){
        RequestHeaderDto dto = requestHeaderService.addCommentsToRequest(requestId,request);
        return buildResponseEntity(true, "success",dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/add-environment-report/{companyId}/{requestId}",method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> generateEnvironmentReport( @PathVariable Long companyId,@PathVariable Long requestId,@RequestBody RequestHeaderDto requestHeaderDto) throws Exception {
        return buildResponseEntity(true,"success",
                requestHeaderService.generateEnvironmentReport(requestHeaderDto,companyId,requestId),HttpStatus.OK);
    }
}
