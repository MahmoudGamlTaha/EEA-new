package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactPersonDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
}
