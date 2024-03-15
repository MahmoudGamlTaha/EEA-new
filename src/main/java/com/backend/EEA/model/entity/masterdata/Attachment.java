package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attachment")
@Data
public class Attachment extends BaseHeaderEntityGen {
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "file_field")
    private String fileField;

    @Column(name = "temp")
    private Boolean temp;

    @Column(name = "valid_from")
    private Date validFromDate;

    @Column(name = "valid_to_date")
      private Date validToDate;

    @Column(name = "company_id")
    Long companyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", insertable = false, updatable = false ,  referencedColumnName = "id")
    Company company;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "request_header_id", insertable = false, updatable = false)
    private RequestHeader requestHeader;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "request_fee_id", insertable = false, updatable = false)
    private RequestFees requestFees;

    @Column(name = "request_fee_id")
    private Long requestFeeId;

    @Column(name = "request_header_id")
    private Long requestHeaderId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "request_detail_id")
    private RequestDetail requestDetail;
    @Column(name = "request_detail_id", updatable = false,insertable = false)
    private Long requestDetailId;

    @Column(name="currency_id")
    private Long currencyId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Comment comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "rdf_id", insertable = false, updatable = false)
    private Rdf rdf;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_activate_id", insertable = false, updatable = false ,  referencedColumnName = "id")
    Company companyActivate;

    @Column(name = "rdf_id")
    private Long rdfId;

    @Column(name = "company_activate_id")
    private Long companyActivateId;

    @ManyToOne
    @JoinColumn(name = "company_detail_id",updatable = false,insertable = false)
    private CompanyDetail companyDetail;

    @Column(name = "company_detail_id")
    private Long companyDetailId;
}
