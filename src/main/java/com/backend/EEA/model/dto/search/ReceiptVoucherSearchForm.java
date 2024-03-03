package com.backend.EEA.model.dto.search;

import lombok.Data;

import java.util.Date;

@Data
public class ReceiptVoucherSearchForm extends BaseSearchForm{
    private Long branchId;
    private Long statusId;
    private Date dateFrom;
    private Date dateTo;
    private Long donorId;
    private String donorIdentity;
    private Long sourceId;
    private Long activityId;

    //activity and management department
}
