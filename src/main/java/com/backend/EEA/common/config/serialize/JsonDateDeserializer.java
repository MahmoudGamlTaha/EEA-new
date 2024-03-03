package com.backend.EEA.common.config.serialize;

import com.backend.EEA.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
    public static final String PUBLIC_DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
        if (jsonparser.getText() == null || jsonparser.getText().isEmpty()) return null;
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        format.setLenient(false);
        try {
            return format.parse(jsonparser.getText());
        } catch (ParseException e) {
            throw new BusinessException("date convert error");
        }
    }

}