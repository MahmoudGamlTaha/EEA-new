package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RdfSearchForm extends BaseSearchForm {
    private String code;
    private String name;
    private Long requesterId;
    private Long [] administrativeId;
}
