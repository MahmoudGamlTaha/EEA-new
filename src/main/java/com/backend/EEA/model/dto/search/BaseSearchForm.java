package com.backend.EEA.model.dto.search;

import com.backend.EEA.model.pojos.SortPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchForm {
    private Integer page;
    private Integer size;
    private Long entityId;
    private SortPojo sortPojo;
}
