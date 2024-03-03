package com.backend.EEA.common.advice;

import com.backend.EEA.model.pojos.ResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponsePojo handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        String message = errors
                .stream()
                .map(e -> {
                    String fieldName = (e instanceof FieldError)?((FieldError)e).getField(): e.getObjectName();

                    return String.format("%s: %s", fieldName, e.getDefaultMessage());
                }).collect(Collectors.joining(";"));

        return ResponsePojo.error(message, null);
    }
}
