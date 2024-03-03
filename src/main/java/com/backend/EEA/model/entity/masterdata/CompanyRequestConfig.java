package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "company_request_config")
@Entity
@Getter
@Setter
public class CompanyRequestConfig extends BaseHeaderEntityGen {
    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "company_activity_id")
    private Long companyActivityId;

    @Column(name = "administrative_id")
    private Long administrativeId;

    @Column(name = "administrative_name")
    private String administrativeName;

    @Column(name = "request_type_name")
    private String requestTypeName;

}
