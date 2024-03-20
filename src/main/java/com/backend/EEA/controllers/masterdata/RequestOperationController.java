package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CommentsDto;
import com.backend.EEA.model.dto.masterdata.RequestHeaderDto;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.RequestHeaderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/portal-data/request-operation")
public class RequestOperationController extends BaseRestController<RequestHeader, RequestHeaderDto, RequestHeaderSearchForm> {
    private RequestHeaderService requestHeaderService;

    public RequestOperationController(RequestHeaderService requestHeaderService) {
        super(requestHeaderService);
    }

    @RequestMapping(value = "/change-harbor", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> changeHarbor(){
return null;
    }
    @RequestMapping(value = "add-comments-to-request/{requestId}", method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> addCommentsToRequest(@PathVariable Long requestId, @RequestBody List<CommentsDto> commentsList ){
        RequestHeaderDto dto = requestHeaderService.addCommentsToRequest(requestId,commentsList);
        return buildResponseEntity(true, "success",dto, HttpStatus.OK);
    }
    @RequestMapping(value = "/status-request-list", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> getAllRequestStatus(){
        return buildResponseEntity(true, "success", CustomerRequestStatus.values(), HttpStatus.OK);
    }



}
