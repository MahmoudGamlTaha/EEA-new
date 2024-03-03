package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggerSearchForm extends BaseSearchForm {
    private Long requestId;
    private Long administrativeId;
    private Long userId;
}
