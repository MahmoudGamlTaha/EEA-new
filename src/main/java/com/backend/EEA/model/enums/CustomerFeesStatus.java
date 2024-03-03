package com.backend.EEA.model.enums;

public enum CustomerFeesStatus {
    PAID("PAID"), UNPAID("UNPAID"), PaidConfirmed("PAID_CONFIRMED");
    final String paymentStatus;
    CustomerFeesStatus(String value){
        this.paymentStatus = value;

    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
