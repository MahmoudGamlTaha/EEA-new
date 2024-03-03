package com.backend.EEA.model.enums;

public enum FilterTypeEnum {

    CONTAINS("1"), EQUALS("2"), NOT_EQUALS("3"), STARTS_WITH("4"), ENDS_WITH("5");

    private final String filterType;

    FilterTypeEnum(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }
}
