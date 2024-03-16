package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "request_fees")
public class RequestFees extends BaseHeaderEntityGen {
    @Column(name = "edara_fee")
    Double edaraFees;
    @Column(name = "quantity")
    Double totalTon;

    @Column(name = "ton_price")
    Double tonPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currencyId_for_ton", insertable = false, updatable = false)
    Currency currency;

    @Column(name = "currencyId_for_ton")
    Long currencyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_rate_id")
    CurrencyRate currencyRate;

    @Column(name = "ratio_edra_fee")
    Double ratioEdaraFee;

    @Column(name = "request_id")
    Long requestId;

    @Column(name = "paid_status")
    String paidStatus;
    @Column(name = "total_fees")
    Double totalFees;
    @Column(name = "rdf_fees")
    Double rdfFees;
    @Column(name = "invoice_number")
    String invoiceNumber;
    @Column(name = "pay_type")
    String payType;
}