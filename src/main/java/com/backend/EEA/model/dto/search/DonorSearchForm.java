package com.backend.EEA.model.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonorSearchForm extends BaseSearchForm{
    String name;
    String identity;
    String mobileNumber;
}
