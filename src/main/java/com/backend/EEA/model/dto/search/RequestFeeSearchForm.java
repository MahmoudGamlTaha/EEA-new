package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFeeSearchForm extends BaseSearchForm {

    private Long requesterId;

    private Long requestId;
}
