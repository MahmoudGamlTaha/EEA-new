package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.entity.BaseDetailEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_activate")
public class CompanyActivate extends BaseHeaderEntityGen {
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "commercial_number")
    private String commercialNumber;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "job")
    private String job;

    @Column(name = "national_number")
    private String nationalNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "company_id")
    private Long companyId;

    @OneToMany(mappedBy = "companyActivate")
    private List<Attachment> attachments;

}