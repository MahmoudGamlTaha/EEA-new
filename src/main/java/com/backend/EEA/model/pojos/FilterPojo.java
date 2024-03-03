package com.backend.EEA.model.pojos;

public class FilterPojo {
    private String fieldName;
    private String filter;
    private String type;

    public FilterPojo() {
    }

    public FilterPojo(String fieldName, String filter, String type) {
        this.fieldName = fieldName;
        this.filter = filter;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
