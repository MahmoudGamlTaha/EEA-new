package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
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
public class CompanyActivateDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String customerName;

    private String commercialNumber;

    private String taxNumber;
    @Email
    private String email;

    private String job;

    private String nationalNumber;

    @Length(min = 6, max = 15)
    private String phoneNumber;

    @Length(min = 1, max = 4)
    private String cityCode;

    private Long userId;

    private Long ownerId;

    private Long companyId;

    private List<AttachmentDto> attachments;

}
