package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashBoardDto {
    private Long acceptedRequests;

    private Long rejectedRequests;

    private Double totalPaid;

    private Long underReviewRequests;

    private String type;
}
