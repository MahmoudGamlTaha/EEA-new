package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministrativeStructureSearchForm extends BaseSearchForm {
    private String name;
    private Long parentId;
    private String parentName;
}
