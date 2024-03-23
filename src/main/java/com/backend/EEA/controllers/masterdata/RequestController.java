package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.*;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.RequestHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/portal/customer-request")
public class RequestController extends BaseRestController<RequestHeader, RequestHeaderDto, RequestHeaderSearchForm> {

    private RequestHeaderService requestHeaderService;

    public RequestController(RequestHeaderService requestHeaderService) {
        super(requestHeaderService);
        this.requestHeaderService = requestHeaderService;
    }
    @RequestMapping(value = "/change-status", method = RequestMethod.PUT)
    public ResponseEntity<ResponsePojo> changeStatus(@RequestParam(value = "requestId") Long requestId, @RequestParam(value = "status")
    CustomerRequestStatus status,@RequestBody(required = false) String comment){
        Boolean changed =  this.requestHeaderService.changeRequestStatus(requestId, status);
         return buildResponseEntity(true, "success",changed, HttpStatus.OK);
    }
    @RequestMapping(value = "/track-request", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> trackCustomerRequest(@RequestParam(value = "requestId") Long requestId){
        List<RequestHeaderTrackingDto> trackingDtoList = this.requestHeaderService.trackCustomerRequest(requestId);
        return buildResponseEntity(true, "success",trackingDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/track-request-status", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> trackRequestStatus(@RequestParam(value = "requestId") Long requestId){
        List<RequestStatusTrackingDto> requestStatusTrackingDto = this.requestHeaderService.requestStatusTracking(requestId);
        return buildResponseEntity(true, "success",requestStatusTrackingDto, HttpStatus.OK);
    }
    @PostMapping(value = "/check-field")
    @ResponseBody
    public ResponseEntity<ResponsePojo> checkField(@RequestBody ErrorDto fields)
    {
        Boolean fieldErrorDtos = this.requestHeaderService.checkError(fields, fields.getRequestId());
        return buildResponseEntity(true, "success", fieldErrorDtos, HttpStatus.OK);
    }
    @RequestMapping(value = "/clear-error", method = RequestMethod.DELETE)
    public ResponseEntity<ResponsePojo> deleteField(@RequestBody ErrorDto clears)
    {
        Boolean success = this.requestHeaderService.DeleteError(clears.getClears(), clears.getRequestId());
        return buildResponseEntity(true, "success", success, HttpStatus.OK);
    }
    @RequestMapping(value = "/list-fields", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> checkField(@RequestParam Long requestId)
    {
        List<FieldErrorDto> fieldErrorDtos = this.requestHeaderService.findCheckError(requestId);
        return buildResponseEntity(true, "success", fieldErrorDtos, HttpStatus.OK);
    }
    @RequestMapping(value = "/assign-administrative", method = RequestMethod.PUT)
    public ResponseEntity<ResponsePojo> changeAdministrative(@RequestParam(value = "requestId") Long requestId, @RequestParam(value = "status") CustomerRequestStatus status){
        Boolean changed =  this.requestHeaderService.changeRequestStatus(requestId, status);
        return buildResponseEntity(true, "success",changed, HttpStatus.OK);
    }
    @RequestMapping(value = "/calculate-charge", method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> calculateCharge(@RequestParam(value = "requestId") Long requestId, @RequestParam(value = "currencyRate") Double currencyRate){
         RequestFeesDto requestFeesDto = this.requestHeaderService.calculateCharge(requestId, currencyRate);
         return buildResponseEntity(true, "success", requestFeesDto,HttpStatus.OK);
    }
    @RequestMapping(value = "/request-change-harbor/{requestId}",method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> createRequestToChangeHarbor(@Valid @RequestBody RequestToChangeHarborDto dto,@PathVariable Long requestId){
        requestHeaderService.createRequestToChangeHarborOrCompleteQuantity(dto,requestId,4L);
        return buildResponseEntity(true,"success",null,HttpStatus.OK);
    }

    @RequestMapping(value = "/request-complete-quantity/{requestId}",method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> createRequestToCompleteQuantity(@Valid @RequestBody RequestToChangeHarborDto dto,@PathVariable Long requestId){
        requestHeaderService.createRequestToChangeHarborOrCompleteQuantity(dto,requestId,5L);
        return buildResponseEntity(true,"success",null,HttpStatus.OK);
    }

    @RequestMapping(value = "/request-approval-developed-form/{requestId}",method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> requestApprovalOfTheDevelopedForm(@Valid @RequestBody RequestDetailDto dto,@PathVariable Long requestId){
        requestHeaderService.requestApprovalOfTheDevelopedForm(dto,requestId);
        return buildResponseEntity(true,"success",null,HttpStatus.OK);
    }


    @GetMapping("/request-model/{id}")
    public ResponseEntity<ResponsePojo> getRequestModel(@PathVariable Long id){
        RequestHeaderModelDto requestHeaderModelDto = requestHeaderService.getRequestModel(id);
        return buildResponseEntity(true, "success", requestHeaderModelDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/plant-coal/{requestTypeId}",method = RequestMethod.POST)
    public ResponseEntity<ResponsePojo> createRequestForApprovalToExportCharcoal(@RequestBody RequestHeaderDto dto,@PathVariable Long requestTypeId){
        return buildResponseEntity(true,"success",requestHeaderService.
                createRequestForApprovalToExportCharcoal(dto,requestTypeId),HttpStatus.OK);
    }

}
