package com.backend.EEA.model.dto.masterdata;


import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDetailDto{

    private Long id;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date dateCreated;


    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date coalStartDate;


    private Integer approvalNumber;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date  approvalDate;

    private List<AttachmentDto> attachment;

    private Long attachmentId;

    private Integer  permitNumber;

    private Date  expirationFrom;

    private Date  expirationTo;

    private CompanyDto company;

    private Long companyId;

    private String headOfDirectors;

    private String areaManger;

    private Integer totalEmployment;

    private Double totalArea;

    private Double storageAreaSpace;
}
