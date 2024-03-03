package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company_status_flow_config")
public class CompanyStatusFlowConfig extends BaseHeaderEntityGen {
    @Column(name = "request_status")
    CustomerRequestStatus customerRequestStatus;

    @Column(name = "request_type_id")
    Long requestTypeId;

    @Column(name = "company_activity_id")
     Long companyActivityId;

    @Column(name = "administrative_id")
    Long administrativeId;
}
