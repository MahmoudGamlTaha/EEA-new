package com.backend.EEA.model.pojos;

import java.util.ArrayList;
import java.util.List;

public class SearchParPojo {
    private Integer page;
    private Integer size;
    private List<FilterPojo> filtersList = new ArrayList<>();
    private SortPojo sortObject;
    private boolean syncWithDatasources = false;

    public SearchParPojo() {
    }

    public SearchParPojo(Integer page, Integer size, List<FilterPojo> filtersList, SortPojo sortObject) {
        this.page = page;
        this.size = size;
        this.filtersList = filtersList;
        this.sortObject = sortObject;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<FilterPojo> getFiltersList() {
        return filtersList;
    }

    public void setFiltersList(List<FilterPojo> filtersList) {
        this.filtersList = filtersList;
    }

    public SortPojo getSortObject() {
        return sortObject;
    }

    public void setSortObject(SortPojo sortObject) {
        this.sortObject = sortObject;
    }

    public boolean isSyncWithDatasources() {
        return syncWithDatasources;
    }

    public void setSyncWithDatasources(boolean syncWithDatasources) {
        this.syncWithDatasources = syncWithDatasources;
    }

}
