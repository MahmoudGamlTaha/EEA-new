package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Company;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyContactDto{

    private Long id;

    private String entityPhoneNumber;

    private String  faxNumber;

    private String email;

    private String website;

    private String personContactWith;

    private String position;

    private String phoneNumber;

    private String personEmail;
    private CompanyDto company;
    private Long companyId;
}
