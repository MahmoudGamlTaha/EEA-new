package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.entity.masterdata.Currency;
import com.backend.EEA.model.entity.masterdata.CurrencyRate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestFeesInvoiceDto {
    String EntityName;
    String establishCode;
    String requesterName;
    String paymentType;
    String invoiceNumber;
    String FeesInArabic;
    String companyName;
    String companyNumber;
    Long requestId;
    Double totalFee;
    List<RequestFeesInvoiceDetailDto> requestFeesInvoiceDetailDtoList;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    Date createdDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    Date dateFrom;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    Date dateTo;

}
