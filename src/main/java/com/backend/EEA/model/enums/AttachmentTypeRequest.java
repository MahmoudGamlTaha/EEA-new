package com.backend.EEA.model.enums;

public enum AttachmentTypeRequest {


INSURANCE_POLICY("INSURANCE_POLICY"), COMPANY_CONTRACT_COAL("COMPANY_CONTRACT_COAL"),
    SHIP_REGISTRY("SHIP_REGISTRY"), HOOK("HOOK_ACCEPT_PAPER"),
    UNLOAD_ACCEPT_PAPER("UNLOAD_ACCEPT_PAPER"),
    STORE_ACCEPT_PAPER("STORE_ACCEPT_PAPER"),
    STORE_INTERMEDIATE_PAPER("STORE_INTERMEDIATE_PAPER"),
    TRANSPORT_ACCEPT_PAPER("TRANSPORT_ACCEPT_PAPER"),
    HARBOR_LANDING_RIVER("HARBOR_LANDING_RIVER"),
    INVOICE("INVOICE"),
    TAX_REGISTER_DOC("TAX_REGISTER_DOC"),
    INDUSTRIAL_REGISTER_DOC("INDUSTRIAL_REGISTER_DOC"),
    RENEW_PERMIT_DOC("RENEW_PERMIT_DOC"),
    NATIONAL_ID("NATIONAL_ID"),
    ANY("ANY"),
    ACCEPT_EEA("ACCEPT_EEA"),
    COMMERCIAL_NUMBER_PAPER("COMMERCIAL_NUMBER_PAPER"),
    COPY_OF_OLD_APPROVAL("Copy of old approval"),
    COPY_OF_OLD_APPROVAL_BACK("Copy of old approval back"),
    Evidence_OF_CANCELLATION_OF_THE_OLD_CONTRACT("Evidence of cancellation of the old contract"),
    Evidence_OF_CANCELLATION_OF_THE_NEW_CONTRACT("Evidence of cancellation of the new contract");

    private final String requestType;

    AttachmentTypeRequest(String requestType){
        this.requestType = requestType;
    }
    public String getRequestType(){
        return requestType;
    }


    public String name(AttachmentTypeRequest request){
        return request.toString();
    }



}
