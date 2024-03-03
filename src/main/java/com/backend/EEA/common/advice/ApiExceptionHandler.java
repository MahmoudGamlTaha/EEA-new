package com.backend.EEA.common.advice;

import com.backend.EEA.common.exceptions.ResourceNotFoundException;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.model.pojos.ResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponsePojo processNotFoundException(ResourceNotFoundException ex) {
        String defaultMessage = ex.getMessage() == null ? "resource is not found": ex.getMessage();
        return ResponsePojo.error(messageSource.getMessage(ex.getMessage(), null, defaultMessage, LocaleContextHolder.getLocale()), null);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponsePojo processBusinessException(BusinessException ex) {
        String defaultMessage = ex.getMessage() == null ? "bad request check params and/or body": ex.getMessage();
        return ResponsePojo.error(messageSource.getMessage(ex.getMessage(), null, defaultMessage, LocaleContextHolder.getLocale()), null);
    }

}
