package com.gantzgulch.tools.reqres.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.domain.AbstractJsonObject;

public class PagedSearchResponse<T> extends AbstractJsonObject {

    @JsonProperty("page")
    private Integer page;
    
    @JsonProperty("per_page")
    private Integer perPage;
    
    @JsonProperty("total")
    private Integer total;
    
    @JsonProperty("total_pages")
    private Integer totalPages;
    
    @JsonProperty("data")
    private List<T> data;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    
}
