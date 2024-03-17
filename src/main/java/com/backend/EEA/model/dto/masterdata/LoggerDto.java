package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class LoggerDto {
    private String EmployeeName;

    private String administrativeName;

    private String action;

    private int order;

    private String table;

    private String rowId;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date createdDate;
}
