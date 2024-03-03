package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.enums.CompanyRequestStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String commercialNumber;

    private String managerName;

    private String name;

    private String importCardNumber;

    private Long activityId;

    private CompanyActivityDto activity;
    @NotNull
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean acceptEEA;
  //  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double quota;

    private Long acceptEEANumber;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date quotaValidFrom;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date quotaValidTo;

    @Length(min = 6, max = 15)
    private String phoneNumber;

    private GovernmentDto governmentDto;
    @Length(max = 3)
    private String cityCode;

    private Long govId;

    @Length(max = 250)
    private String address;

    private CompanyTypeDto companyType;

    private Long companyTypeId;

    private String industryNumber;

    private String taxNumber;

    private String purpose;

    @Email
    private String email;

    private String renewPermitStatus;

    private List<AttachmentDto> attachments;

    private Long ownerId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyRequestStatus status;

}
