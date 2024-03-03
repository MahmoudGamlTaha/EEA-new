package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDetailDto {

 //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
 private long id;

 List<AttachmentDto> otherAttachment;

 CompanyDto company;
 private Long companyId;

 private Long companyActivityId;

 CompanyActivityDto companyActivity;

 Long intermediateStoreId;

 @NumberFormat
 String companyAcceptanceNumber;

 private AttachmentDto companyAcceptance;

 private Date acceptDate;
}
