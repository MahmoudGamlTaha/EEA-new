package com.backend.EEA.model.dto.masterdata;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MainActivityDto {
    @NotNull(message = "{idIsRequired}")
    private Long id;
    @NotBlank(message = "{nameIsRequired}")
    private String name;
    @NotBlank(message = "{codeIsRequired}")
    private String code;
    @NotNull(message = "{categoryIdIsRequired}")
    private Long categoryId;
    private Long parentId;
}
