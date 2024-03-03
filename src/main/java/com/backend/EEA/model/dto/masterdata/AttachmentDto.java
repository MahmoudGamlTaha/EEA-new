package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentDto {

    @NotBlank(message = "{idIsRequired}")
    private Long id;

    private String url;

    private String name;

    private String fileField;

    private Long companyId;

    private Long commentId;

    private Long taskId;

    private Long requestHeaderId;
    private Long requestDetailId;
    private Long requestFeeId;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    Date validFromDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
  //  @JsonFormat(pattern="yyyy-MM-dd")
    Date validToDate;
}
