package com.backend.EEA.model.enums;

public enum ResponseMessageEnum {
    SUCCESS("Success"),
    CREATED("Created"),
    UPDATED("Updated"),
    DELETED("Deleted"),
    FAILED("Failed"),
    NOT_FOUND("NOT_FOUND");

    private final String message;

    ResponseMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
