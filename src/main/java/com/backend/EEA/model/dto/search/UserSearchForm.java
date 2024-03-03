package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchForm extends BaseSearchForm {
    private String username;
    private String name;
    private String roleCode;
}
