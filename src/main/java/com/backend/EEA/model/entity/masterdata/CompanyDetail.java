package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comany_detail")
public class CompanyDetail extends BaseHeaderEntityGen {

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "coal_start_date")
    private Date coalStartDate;

    @Column(name = "approval_number")
    private Integer approvalNumber;

    @Column(name = "approval_date")
    private Date  approvalDate;

    @OneToMany(mappedBy = "companyDetail")
    private List<Attachment> attachment;

    @Column(name = "permit_number")
    private Integer  permitNumber;

    @Column(name = "expiration_from")
    private Date  expirationFrom;

    @Column(name = "expiration_to")
    private Date  expirationTo;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID",updatable = false,insertable = false)
    private Company company;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "head_of_directors")
    private String headOfDirectors;

    @Column(name = "area_manger")
    private String areaManger;

    @Column(name = "total_employment")
    private Integer totalEmployment;

    @Column(name = "total_area")
    private Double totalArea;

    @Column(name = "storage_area_space")
    private Double storageAreaSpace;
}
