package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.masterdata.Company;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company_contact")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CompanyContact extends BaseHeaderEntityGen {

    @Column(name = "entity_phone_number")
    private String entityPhoneNumber;

    @Column(name = " fax_number")
    private String  faxNumber;

    private String email;

    private String website;

    @Column(name = "person_contact_with")
    private String personContactWith;

    private String position;

    private String phoneNumber;

    private String personEmail;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID",updatable = false,insertable = false)
    private Company company;

    @Column(name = "COMPANY_ID")
    private Long companyId;
}
