package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.masterdata.Currency;
import com.backend.EEA.model.entity.masterdata.CurrencyRate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestFeesDto {

    Double edaraFees;

    Double totalTon;

    Double tonPrice;

    Currency currency;

    Long currencyId;

    CurrencyRate currencyRate;

    Double ratioEdaraFee;

    Double rdfTotal;
    AttachmentDto paidInvoice;
    Long requestId;
    Double totalFee;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    Date createdDate;

}
