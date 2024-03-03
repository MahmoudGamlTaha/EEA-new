package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivitySearchForm extends BaseSearchForm {

    private Long id;
    private String code;
    private String name;
    private Long administrativeStructureId;
    private String countryCode;
    private Long userId;
    private Long constructurerId;
    private Long mainActivityId;
    private String description;
}
