package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestStatusTrackingDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Long administrativeId;
    private Boolean status;
    private String state;
    private Date createdDate;

}
