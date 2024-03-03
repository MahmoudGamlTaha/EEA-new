package com.backend.EEA.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.EEA.model.pojos.ResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class DatabaseExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @Value("classpath:messages/database-errors.json")
    Resource resourceFile;

    Map<String, String> databaseErrors;

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponsePojo processConstraintError(DataIntegrityViolationException ex) {
        try {
            String sqlMessage = (ex.getRootCause() != null)?ex.getRootCause().getMessage(): ex.getMessage();
            String defaultMessage = "error while persisting data, your data is not valid";
            return ResponsePojo.error(messageSource.getMessage(getMessageKey(sqlMessage), null,defaultMessage , LocaleContextHolder.getLocale()), null);
        }catch(IOException ioException) {
            return ResponsePojo.error("can't fetch localied messages of database errors", null);
        }

    }


    private String getMessageKey(String sqlMessage) throws IOException {
        if(databaseErrors == null){
            databaseErrors = new ObjectMapper().readValue(resourceFile.getFile(), Map.class);
        }
        for(String key: databaseErrors.keySet()){
            System.out.println("sqlkey: "+ key);
            if(sqlMessage.toLowerCase().contains(key.toLowerCase()))
                return databaseErrors.get(key);
        }
        return "database.generalError";
    }

}