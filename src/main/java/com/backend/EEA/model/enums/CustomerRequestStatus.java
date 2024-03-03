package com.backend.EEA.model.enums;

public enum CustomerRequestStatus {
        Created("Created"), UnderReview("Under Review"),AcceptRDF("AcceptedRDF"), Rejected("Rejected"),AcceptedInvest("Accepted_Invest"), Accept("Accepted"), AcceptProtectEEA("Accept_Protect_EEA"), AcceptManager("Accept_manager"),CompleteEntry("Complete Entry"), CustomerPAID("Customer Paid"),ConfirmPaymentEEA("confirm Payment EEA");//last one 10
    CustomerRequestStatus(String status){

    }
    String name(CustomerRequestStatus status){
        return status.toString();
    }
}
