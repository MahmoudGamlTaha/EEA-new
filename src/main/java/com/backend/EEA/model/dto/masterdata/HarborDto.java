package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.security.HarborType;
import com.backend.EEA.model.entity.masterdata.Country;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HarborDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String code;
    private HarborType harborType;
    private String name;

    private CountryDto country;

    private Long countryId;
}
