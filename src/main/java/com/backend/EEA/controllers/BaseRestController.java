package com.backend.EEA.controllers;

import com.backend.EEA.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.backend.EEA.model.dto.search.BaseSearchForm;
import com.backend.EEA.model.entity.BaseEntity;
import com.backend.EEA.model.enums.ResponseMessageEnum;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


public abstract class BaseRestController<T extends BaseEntity, X, Z extends BaseSearchForm> {


    @Autowired
    private MessageSource messageSource;

    private BaseService<T, X, Z> baseService;

    public BaseRestController(BaseService<T, X, Z> baseService) {
        this.baseService = baseService;
    }

    @Tag(name = "Search API", description = "This API accepts searchDto as string, and it will find all or find with pagination based on page or page size or sorting is not null")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponsePojo> findListBySpecifications(@RequestParam(name = "search", required = false) String searchDto) {
        Z searchObj;
        try {
            searchObj = baseService.prepareSearchPojo((searchDto != null ? searchDto : "{}"));
        } catch (JsonProcessingException e) {
            throw new BusinessException("search.invalidJson");
        }
        searchObj.setEntityId(baseService.getEntityId());
            if (searchObj.getPage() != null || searchObj.getSize() != null || searchObj.getSortPojo() != null)
                return buildResponseEntity(baseService.findListBySpecificationsWithPagination(searchObj));
            else
                return buildResponseEntity(baseService.findListBySpecifications(searchObj));

    }
    @Tag(name = "Search API", description = "This API accepts searchDto as string, and it will find all or find with pagination based on page or page size or sorting is not null")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponsePojo> findListBySpecifications2(@RequestParam(name = "search", required = false) String searchDto) {
        Z searchObj;
        try {
            searchObj = baseService.prepareSearchPojo((searchDto != null ? searchDto : "{}"));
        } catch (JsonProcessingException e) {
            throw new BusinessException("search.invalidJson");
        }
        searchObj.setEntityId(baseService.getEntityId());
        if (searchObj.getPage() != null || searchObj.getSize() != null || searchObj.getSortPojo() != null)
            return buildResponseEntity(baseService.findListBySpecificationsWithPagination(searchObj));
        else
            return buildResponseEntity(baseService.findListBySpecifications(searchObj));

    }
    @Tag(name = "Search by entity id API", description = "This API accepts entity id as path param, it will return the required entity or exception if not fount")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponsePojo> findById(@PathVariable long id) {
        return buildResponseEntity(baseService.findOne(id));
    }

    @Tag(name = "Create new API", description = "This API will create new entity")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponsePojo> create(@Valid @RequestBody X dtoPojo) throws Exception {
        try {
            return buildResponseEntity(true, null, baseService.createEntity(dtoPojo), HttpStatus.OK);
        } catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }

    @Tag(name = "Edit entity API", description = "This API accepts entity id as path param, it will search for the required entity or exception if not fount, or if it found, then it will edit it")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponsePojo> edit(@Valid @PathVariable long id, @Valid @RequestBody X dtoPojo) throws Exception {
        baseService.editEntity(id, dtoPojo);
        return buildResponseEntity(true, null, ResponseMessageEnum.UPDATED, HttpStatus.OK);
    }

    @Tag(name = "Delete entity API", description = "This API accepts entity id as path param, it will search for the required entity or exception if not fount, or if it found, then it will delete it")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponsePojo> delete(@PathVariable long id) throws Exception {
        baseService.delete(id);
        return buildResponseEntity(true, null, ResponseMessageEnum.DELETED, HttpStatus.OK);
    }

    protected ResponseEntity<ResponsePojo> buildResponseEntity(Object object) {
        return buildResponseEntity(true, null, object, HttpStatus.OK);
    }

    protected ResponseEntity<ResponsePojo> buildResponseEntity(boolean iSuccess, String Message, Object data, HttpStatus httpStatus) {
        ResponsePojo responsePojo = new ResponsePojo(iSuccess, Message, data);
        return new ResponseEntity<>(responsePojo, httpStatus);
    }
    
    protected ResponseEntity<ResponsePojo> buildExceptionResponseEntity(Exception ex) {
    	ex.printStackTrace();
        ResponsePojo responsePojo = new ResponsePojo(false, ex.getMessage(), null);
        return new ResponseEntity<>(responsePojo, HttpStatus.BAD_REQUEST);
    }

}
