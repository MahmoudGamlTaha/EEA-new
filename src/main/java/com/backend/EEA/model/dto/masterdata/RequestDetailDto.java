package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
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

 // Environmental performance report

 @JsonSerialize(using = JsonDateSerializer.class)
 @JsonDeserialize(using = JsonDateDeserializer.class)
 private Date fromDate;

 @JsonSerialize(using = JsonDateSerializer.class)
 @JsonDeserialize(using = JsonDateDeserializer.class)
 private Date toDate;
}
