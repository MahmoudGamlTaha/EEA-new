package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyActivityDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String code;

    @NotBlank(message = "{nameIsRequired}")
    private String name;

    private Long mainActivityId;

    private String description;

    private long userId;
}
