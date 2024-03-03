package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.security.HarborType;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplainsDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String summary;

    private String replies;

    private RequestHeader requestHeader ;

    private Long requesterId;

    private Long requestHeaderId;
}
