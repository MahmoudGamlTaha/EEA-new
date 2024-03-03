package com.backend.EEA.common.config.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Date> {
    public static final String PUBLIC_DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (date != null) jsonGenerator.writeString(new SimpleDateFormat(PUBLIC_DATE_FORMAT).format(date));
    }
}
