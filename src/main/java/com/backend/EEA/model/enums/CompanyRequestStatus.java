package com.backend.EEA.model.enums;

public enum CompanyRequestStatus {
    Created("Created"), UnderReview("Under Review"),Activated("Activated"), Rejected("Rejected");
    CompanyRequestStatus(String status){

    }
    String name(CompanyRequestStatus status){
        return status.toString();
    }
}
