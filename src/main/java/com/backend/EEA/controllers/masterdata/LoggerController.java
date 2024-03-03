package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.LoggerDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.LoggerSearchForm;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.operation.Logger;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.CoalTypeService;
import com.backend.EEA.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("portal-data/logger")
public class LoggerController extends BaseRestController<Logger, LoggerDto, LoggerSearchForm> {
    LoggerService loggerService;
 public LoggerController(LoggerService loggerService){
     super(loggerService);
     this.loggerService = loggerService;
 }
 @RequestMapping(value = "request-log/{requestId}", method = RequestMethod.GET)
 @ResponseBody
 public ResponseEntity<ResponsePojo>findLoggerByRequestId(@PathVariable Long requestId){
     List<LoggerDto> loggerDtos = loggerService.findLogsByRequestId(requestId);
     return this.buildResponseEntity( true,"success",loggerDtos, HttpStatus.OK);
 }
}
