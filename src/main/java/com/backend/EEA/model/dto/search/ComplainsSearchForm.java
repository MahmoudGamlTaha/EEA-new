package com.backend.EEA.model.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplainsSearchForm extends BaseSearchForm {
    private Long requestId;
    private Long requesterId;
}
