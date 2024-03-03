package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "Request_detail")
@Getter
@Setter
@Entity
public class RequestDetail extends BaseHeaderEntityGen {

    @ManyToOne
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private  RequestHeader requestHeader;

    @Column(name = "request_id")
    private Long requestHeaderId;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "company_activity_id", insertable = false, updatable = false)
    private CompanyActivity companyActivity;

    @Column(name = "company_activity_id")
    private Long companyActivityId;

    @ManyToOne
    @JoinColumn(name = "accept_attach_id")
    private Attachment companyAcceptance;
    /// change harbor request
    @ManyToOne
    @JoinColumn(name = "front_image_from_acceptance")
    private Attachment frontImageFromAcceptance;
    private H

    @Column(name = "company_accept_number")
    private Long companyAcceptanceNumber;

    @Column(name = "intermediate_store_id")
    private Long intermediateStoreId;

    @Column(name = "accept_date")
    private Date acceptDate;
    @OneToMany(mappedBy = "requestDetail")
    private Set<Attachment> otherAttachment;
}
