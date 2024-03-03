package com.backend.EEA.model.enums;

public enum SortTypeEnum {
    ASC("asc"), DESC("desc");

    private final String sortType;

    SortTypeEnum(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }
}
