package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.CompanyRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company extends BaseHeaderEntityGen {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "commercial_record_number", unique = true)

    private String commercialNumber;

    @Column(name = "industry_record_number", unique = true)
    private String industryNumber;

    @Column(name = "tax_record_number", unique = true)
    private String taxNumber;

    @Column(name = "import_card_number")
    private String importCardNumber;

    @Column(name = "activity_id")
    private Long activity_id;

    @Column(name = "accept_EEA")
    private boolean acceptEEA;

    @Column(name ="accept_EEA_Number")
    private String acceptEEANumber;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "address")
    private String address;

    @Column(name = "renew_Permit_Status")
    String renewPermitStatus;

    @Column(name = "quota")
    private Double quota;
    @Column(name = "valid_quota_from")
    private Date quotaValidFrom;
    @Column(name = "valid_quota_to")
    private Date quotaValidTo;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "phone_number")
    @NumberFormat
    private String phoneNumber;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "email")
    @Email
    private String email;
    @OneToMany(mappedBy = "company")
    private List<Attachment> attachments;
    @OneToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private CompanyType companyType;

    @Column(name = "type_id")
    private Long companyTypeId;

    @ManyToOne
    @JoinColumn(name = "activity_id", insertable = false, updatable = false)
    private CompanyActivity activity;

    @ManyToOne
    @JoinColumn(name = "gov_id", insertable = false, updatable = false)
    private Government government;

    @Column(name = "gov_id")
    private Long govId;

    @ManyToOne()
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "status")
    private CompanyRequestStatus status;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    List<CompanyContact> companyContacts;

    @OneToMany(mappedBy = "company")
    List<CompanyDetail> companyDetails;
}