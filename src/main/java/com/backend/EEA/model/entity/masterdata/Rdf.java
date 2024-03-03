package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rdf")
public class Rdf extends BaseHeaderEntityGen {
    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "weight_in_ton")
    private Double weightInTon;

    @Column(name = "total_weight_Ton")
    private Double totalWeightInTon;

    @Column(name = "date")
    Date date;

    @Column(name = "provider_name")
    String providerName;

    @Column(name = "company_situation_used")
    Boolean companySituationUsed;

    @Column(name = "includeEnergyReject")
    Boolean includeEnergyReject;

    @Column(name = "company_confirm")
    Boolean companyConfirm;

    @Column(name = "company_id")
    private Long companyId;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private RequestHeader requestHeader;

    @Column(name = "request_id")
    private Long requestId;

    @OneToMany(mappedBy = "rdf", cascade = CascadeType.MERGE)
    List<Attachment> invoices;

    //@Column(name = "administrative_id")
    //private Long administrativeId;
}