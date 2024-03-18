package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestFeesInvoiceDetailDto {
    String service;
    Double amount;
}
