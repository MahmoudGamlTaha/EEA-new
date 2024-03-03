package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONTACT_PERSON")
public class ContactPerson extends BaseDetailEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Email
    @Column(name = "EMAIL")
    private String email;


}
