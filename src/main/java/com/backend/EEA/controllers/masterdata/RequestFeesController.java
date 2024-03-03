package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.RequestFeesDto;
import com.backend.EEA.model.dto.search.RequestFeeSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.RequestFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/request-fees")
public class RequestFeesController extends BaseRestController<RequestFees, RequestFeesDto, RequestFeeSearchForm> {

    private RequestFeeService requestFeeService;

    public RequestFeesController(RequestFeeService requestFeeService) {
        super(requestFeeService);
        this.requestFeeService = requestFeeService;
    }
    @GetMapping(value = "/fees-by-request")
    public ResponseEntity<ResponsePojo> findRdfByRequestId(@RequestParam("requestId") Long requestId){
        RequestFees requestFees = this.requestFeeService.findFeesByRequestId(requestId);
        return buildResponseEntity(true, "success",requestFees, HttpStatus.OK);
    }


}
