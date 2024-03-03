package com.backend.EEA.model.entity.operation;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.masterdata.Company;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "company_quota")
@Data
public class CompanyQuota extends BaseHeaderEntityGen {
    private Double quota;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id")
    private Long companyId;
}
