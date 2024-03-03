package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainActivitySearchForm extends BaseSearchForm {
    private Long id;
    private String name;
    private String code;
    private Long categoryId;
    private Long parentId;
}
