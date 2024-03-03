package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainActivityCategorySearchForm extends BaseSearchForm {
    private String id;
    private String userId;
    private String name;
}
